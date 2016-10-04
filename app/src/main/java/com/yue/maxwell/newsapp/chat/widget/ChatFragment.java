package com.yue.maxwell.newsapp.chat.widget;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.adapter.ChatListAdapter;
import com.yue.maxwell.newsapp.base.BaseFragment;
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
import rx.Observable;
import rx.Observer;

/**
 * 2016/9/26 0026，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ChatFragment<T> extends BaseFragment implements ChatView {

    @BindView(R.id.lv_fragment_chat)
    ListView mListView;
    @BindView(R.id.et_fragment_chat)
    EditText mEditText;
    @BindView(R.id.tv_fragment_chat_send)
    TextView mTvSend;

    private List<ChatMsg<T>> mMsgList = new ArrayList<>();

    private ChatListAdapter mListAdapter;

    private ChatPresenter mChatPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChatPresenter = new ChatPresenterImpl(this);
    }

    @Override
    public int getLayoutId() {

        return R.layout.fragment_chat;
    }

    @Override
    public void initData() {

        mListAdapter = new ChatListAdapter(getActivity(), mMsgList);
        mListView.setAdapter(mListAdapter);

        sayHello();

    }

    private void sayHello() {
        //Rxjava的定时任务的一种应用场景：用户点聊天按钮后1s执行
        Observable.timer(1, TimeUnit.SECONDS)
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
        mListView.setSelection(mListAdapter.getCount()-1);
    }

    @Override
    public void initEvent() {
        mTvSend.setOnClickListener((v) -> {
            //
            String s = mEditText.getText().toString().trim();
            if(!TextUtils.isEmpty(s)){
                addMsgItem(new ChatMsg(ChatMsg.Type.INPUT, s));
                mChatPresenter.getReplyMsg(mEditText.getText().toString().trim());
                mEditText.setText("");
            }


        });
    }
}
