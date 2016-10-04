package com.yue.maxwell.newsapp.chat.widget;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

public class ChatFragment<T> extends BaseFragment{

    @BindView(R.id.bt_fragment_chat_goto)
    Button mBt;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat;
    }


    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mBt.setOnClickListener((v) ->{
            Intent intent = new Intent(getActivity(), ChatListActivity.class);
            startActivity(intent);
        });
    }

}
