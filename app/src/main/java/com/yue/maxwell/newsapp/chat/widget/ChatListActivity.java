package com.yue.maxwell.newsapp.chat.widget;

import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.adapter.ChatListAdapter;
import com.yue.maxwell.newsapp.base.BaseActivity;
import com.yue.maxwell.newsapp.bean.ChatMsg;
import com.yue.maxwell.newsapp.bean.ChatTextBean;
import com.yue.maxwell.newsapp.chat.presenter.ChatPresenter;
import com.yue.maxwell.newsapp.chat.presenter.ChatPresenterImpl;
import com.yue.maxwell.newsapp.chat.view.ChatView;
import com.yue.maxwell.newsapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 2016/10/4 0004，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ChatListActivity<T extends Parcelable> extends BaseActivity implements ChatView {

    @BindView(R.id.lv_activity_chat)
    ListView mListView;
    @BindView(R.id.et_activity_chat)
    EditText mEditText;
    @BindView(R.id.tv_activity_chat_send)
    TextView mTvSend;
    @BindView(R.id.iv_toolbar_back)
    ImageView mIvBack;

    //private List<ChatMsg<T>> mCacheList = new ArrayList<>();

    private List<ChatMsg<T>> mMsgList = new ArrayList<>();

    private ChatListAdapter mListAdapter;

    private ChatPresenter mChatPresenter;
    @Override
    public void initView() {
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        mChatPresenter = new ChatPresenterImpl(this);
        mListAdapter = new ChatListAdapter(this, mMsgList);
        mListView.setAdapter(mListAdapter);
        sayHello();//设置一些欢迎语


    }


    private void sayHello() {
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
        Observable.just(getString(R.string.chat_init_hint1), getString(R.string.chat_init_hint2))
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
                        addMsgItem(new ChatMsg(ChatMsg.Type.OUTPUT_TEXT, bean));
                    }
                });


    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showShort(errorMsg);
    }

    @Override
    public void addMsgItem(ChatMsg replyMsg) {
        mMsgList.add(replyMsg);
        mListAdapter.notifyDataSetChanged();
        mListView.setSelectionFromTop(mListAdapter.getCount()-1, 0);
    }

    @Override
    public void initEvent() {

        mTvSend.setOnClickListener((v) -> {

            String s = mEditText.getText().toString().trim();
            if(!TextUtils.isEmpty(s)){
                ChatTextBean bean = new ChatTextBean();
                bean.setText(s);
                addMsgItem(new ChatMsg(ChatMsg.Type.INPUT, bean));
                mChatPresenter.getReplyMsg(mEditText.getText().toString().trim());
                mEditText.setText("");
            }
        });

        mIvBack.setOnClickListener((v) -> {finish();});

    }
}
