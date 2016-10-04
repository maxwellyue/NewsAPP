package com.yue.maxwell.newsapp.chat.presenter;

import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.yue.maxwell.newsapp.bean.ChatHrefBean;
import com.yue.maxwell.newsapp.bean.ChatMenuBean;
import com.yue.maxwell.newsapp.bean.ChatMsg;
import com.yue.maxwell.newsapp.bean.ChatNewsBean;
import com.yue.maxwell.newsapp.bean.ChatTextBean;
import com.yue.maxwell.newsapp.chat.ChatService;
import com.yue.maxwell.newsapp.chat.view.ChatView;
import com.yue.maxwell.newsapp.common.Constants;
import com.yue.maxwell.newsapp.net.NetManager;


import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
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

public class ChatPresenterImpl<T extends Parcelable> implements ChatPresenter {

    private static final String TAG = ChatPresenterImpl.class.getSimpleName();
    private ChatView mChatView;
    private ChatService mChatService;


    public ChatPresenterImpl(ChatView chatView){
        this.mChatView = chatView;
        this.mChatService = (ChatService) NetManager.getInstance().createString(ChatService.class);
    }

    @Override
    public void getReplyMsg(String info) {

        Map<String, Object> map = new ArrayMap<>();
        map.put("key", Constants.TULING_APIKEY);
        map.put("info", info);
        map.put("loc", "济南");
        map.put("userid", "asfasdfasdf");

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(map)).toString());
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

    private ChatMsg<T> convertJson(String s){

        ChatMsg<T> msg = new ChatMsg<T>();
        Gson gson = new Gson();

        if(s.contains("\"code\":100000")){
            ChatTextBean textBean = gson.fromJson(s, ChatTextBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_TEXT);
            msg.setMsg((T) textBean);

        }else if(s.contains("\"code\":200000")){
            ChatHrefBean hrefBean =  gson.fromJson(s, ChatHrefBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_HREF);
            msg.setMsg((T) hrefBean);

        }else if(s.contains("\"code\":302000")){
            ChatNewsBean newsBean = gson.fromJson(s, ChatNewsBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_NEWS);
            msg.setMsg((T) newsBean);
        }else if(s.contains("\"code\":308000")){
            ChatMenuBean menuBean = gson.fromJson(s, ChatMenuBean.class);
            msg.setType(ChatMsg.Type.OUTPUT_MENU);
            msg.setMsg((T) menuBean);
        }else if(s.contains("\"code\":40002")) {
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
