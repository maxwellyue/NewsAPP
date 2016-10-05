package com.yue.maxwell.newsapp.chat.widget;

import android.text.TextUtils;
import android.view.View;
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
import com.yue.maxwell.newsapp.utils.FileUtil;
import com.yue.maxwell.newsapp.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Observable;
import rx.Observer;
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

public class ChatListActivity<T> extends BaseActivity implements ChatView<T> {

    @BindView(R.id.lv_activity_chat)
    ListView mListView;
    @BindView(R.id.et_activity_chat)
    EditText mEditText;
    @BindView(R.id.tv_activity_chat_send)
    TextView mTvSend;
    @BindView(R.id.iv_toolbar_back)
    ImageView mIvBack;
    @BindView(R.id.iv_toolbar_delete)
    ImageView mIvDelete;


    private List<ChatMsg<T>> mMsgList = new ArrayList<>();

    private ChatListAdapter<T> mListAdapter;

    private ChatPresenter mChatPresenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        mChatPresenter = new ChatPresenterImpl<>(this);
        mListAdapter = new ChatListAdapter<>(this, mMsgList);
        mListView.setAdapter(mListAdapter);
        mChatPresenter.loadLocalData(this);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showShort(errorMsg);
    }

    @Override
    public void clearMsg() {
        mMsgList.clear();
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMsgItem(ChatMsg<T> replyMsg) {
        mMsgList.add(replyMsg);
        notifyChange();

    }

    @Override
    public void addMsgItem(List<ChatMsg<T>> replyMsg) {
        mMsgList.addAll(replyMsg);
        notifyChange();
    }

    private void notifyChange() {
        mListAdapter.notifyDataSetChanged();
        mListView.setSelectionFromTop(mListAdapter.getCount() - 1, 0);
    }

    @Override
    public void initEvent() {

        mTvSend.setOnClickListener((v) -> {

            String s = mEditText.getText().toString().trim();
            if (!TextUtils.isEmpty(s)) {
                ChatTextBean bean = new ChatTextBean();
                bean.setText(s);
                addMsgItem(new ChatMsg<T>(ChatMsg.Type.INPUT, (T) bean));
                mChatPresenter.getReplyMsg(mEditText.getText().toString().trim());
                mEditText.setText("");
            }
        });

        mIvBack.setOnClickListener((v) -> finish());
        mIvDelete.setOnClickListener((v) -> {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("确定要删除聊天记录吗?")
                    .setContentText("删除之后就不能恢复了哦")
                    .setConfirmText("就要删")
                    .setCancelText("我再想想")
                    .setConfirmClickListener((sDialog) -> {
                        mChatPresenter.clearMsg(this);
                        sDialog.dismissWithAnimation();
                    })
                    .setCancelClickListener((sDialog) -> sDialog.cancel())
                    .show();

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mChatPresenter.saveMsgToLocal(this, mMsgList);

    }
}
