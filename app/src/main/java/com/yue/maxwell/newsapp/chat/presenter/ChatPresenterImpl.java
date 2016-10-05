package com.yue.maxwell.newsapp.chat.presenter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.application.NewsApplication;
import com.yue.maxwell.newsapp.bean.ChatHrefBean;
import com.yue.maxwell.newsapp.bean.ChatMenuBean;
import com.yue.maxwell.newsapp.bean.ChatMsg;
import com.yue.maxwell.newsapp.bean.ChatNewsBean;
import com.yue.maxwell.newsapp.bean.ChatTextBean;
import com.yue.maxwell.newsapp.chat.ChatService;
import com.yue.maxwell.newsapp.chat.view.ChatView;
import com.yue.maxwell.newsapp.chat.widget.ChatListActivity;
import com.yue.maxwell.newsapp.common.Constants;
import com.yue.maxwell.newsapp.net.NetManager;
import com.yue.maxwell.newsapp.utils.DeviceUtil;
import com.yue.maxwell.newsapp.utils.FileUtil;
import com.yue.maxwell.newsapp.utils.SPUtil;
import com.yue.maxwell.newsapp.utils.ToastUtil;


import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 2016/10/3 0003，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ChatPresenterImpl<T> implements ChatPresenter<T> {

    private static final String TAG = ChatPresenterImpl.class.getSimpleName();
    private ChatView<T> mChatView;
    private ChatService mChatService;

    public ChatPresenterImpl(ChatView<T> chatView) {
        this.mChatView = chatView;
        this.mChatService = (ChatService) NetManager.getInstance().createString(ChatService.class);
    }

    @Override
    public void getReplyMsg(String info) {

        Map<String, Object> map = new ArrayMap<>();
        map.put("key", Constants.TULING_APIKEY);
        map.put("info", info);
        //map.put("loc", "济南");
        map.put("userid", SPUtil.getString(NewsApplication.getContext(), Constants.USER_NAME, ""));

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(map)).toString());
        mChatService.chatMsg(body)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onError(Throwable e) {
                        mChatView.showErrorMsg(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(String s) {

                        mChatView.addMsgItem(convertJson(s));

                        //手动解析json
                        Log.e(TAG, "onNext: " + s);
                    }
                });

    }

    @Override
    public void loadLocalData(Context context) {
        Observable.create(new Observable.OnSubscribe<List<ChatMsg<T>>>() {

            @Override
            public void call(Subscriber<? super List<ChatMsg<T>>> subscriber) {
                FileUtil util = new FileUtil();
                List<ChatMsg<T>> list = util.loadChatMsgFromLocal(context);
                if (list != null && list.size() > 0) {
                    subscriber.onNext(list);
                } else {
                    subscriber.onError(new Throwable());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ChatMsg<T>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        sayHello(context);
                    }

                    @Override
                    public void onNext(List<ChatMsg<T>> chatMsgs) {
                        mChatView.addMsgItem(chatMsgs);
                    }
                });


    }

    @Override
    public void saveMsgToLocal(Context context, List<ChatMsg<T>> msgList) {
        Observable.create((Boolean) -> {

            FileUtil<T> util = new FileUtil<T>();
            util.saveChatMsgToLocal(context, msgList);
            Log.e(TAG, "saveMsgToLocal: 缓存成功");
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();//这里必须订阅，否则事件将不会发射
    }

    @Override
    public void clearMsg(Context context) {
        //View改变
        mChatView.clearMsg();
        //数据改变
        FileUtil.clearLocalChatMsg(context);

    }

    private void sayHello(Context context) {
        //Rxjava的定时任务的一种应用场景
       /*
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long number) {
                        ChatTextBean bean = new ChatTextBean();
                        bean.setText(getString(R.string.chat_init_hint));

                        addMsgItem(new ChatMsg(ChatMsg.Type.OUTPUT_TEXT, bean));

                    }
                });*/
        //Rxjava的任务队列的一种应用场景
        Observable.just(context.getString(R.string.chat_init_hint1), context.getString(R.string.chat_init_hint2))
                .delay(1, TimeUnit.SECONDS)//延时1s再发射
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        ChatTextBean bean = new ChatTextBean();
                        bean.setText(s);
                        mChatView.addMsgItem(new ChatMsg<T>(ChatMsg.Type.OUTPUT_TEXT, (T) bean));

                    }
                });


    }

    private ChatMsg<T> convertJson(String s) {

        ChatMsg<T> msg = new ChatMsg<T>();
        Gson gson = new Gson();

        if (s.contains("\"code\":100000")) {
            ChatTextBean textBean = gson.fromJson(s, ChatTextBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_TEXT);
            msg.setMsg((T) textBean);

        } else if (s.contains("\"code\":200000")) {
            ChatHrefBean hrefBean = gson.fromJson(s, ChatHrefBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_HREF);
            msg.setMsg((T) hrefBean);

        } else if (s.contains("\"code\":302000")) {
            ChatNewsBean newsBean = gson.fromJson(s, ChatNewsBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_NEWS);
            msg.setMsg((T) newsBean);
        } else if (s.contains("\"code\":308000")) {
            ChatMenuBean menuBean = gson.fromJson(s, ChatMenuBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_MENU);
            msg.setMsg((T) menuBean);
        } else if (s.contains("\"code\":40002")) {
            ChatTextBean textBean = gson.fromJson(s, ChatTextBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_TEXT);
            msg.setMsg((T) textBean);
        }

        /*String jsonInput = "{\"access_token\": \"abcdefgh\"}";

    JsonElement je = new JsonParser().parse(jsonInput);

    String value = je.getAsJsonObject().get("access_token").getAsString();
    System.out.println(value);*/

        return msg;
    }


}
