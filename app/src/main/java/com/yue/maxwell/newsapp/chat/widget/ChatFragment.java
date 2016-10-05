package com.yue.maxwell.newsapp.chat.widget;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.base.BaseFragment;
import com.yue.maxwell.newsapp.common.Constants;
import com.yue.maxwell.newsapp.utils.FileUtil;
import com.yue.maxwell.newsapp.utils.SPUtil;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Observable;
import rx.Subscriber;

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

public class ChatFragment extends BaseFragment {

    @BindView(R.id.tv_fragment_chat_goto)
    TextView mTvGo;
    @BindView(R.id.et_fragment_chat_name)
    EditText mEtUserName;
    @BindView(R.id.et_fragment_chat_pwd)
    EditText mEtUserPwd;
    @BindView(R.id.cb_fragment_chat_remember_pwd)
    CheckBox mCbRememberPwd;


    private boolean mHasUser;
    private boolean mRememberPwd;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initData() {

        //用户信息展示
        mHasUser = SPUtil.getBoolean(getActivity(), Constants.HAS_USER, false);
        mRememberPwd = getRememberState();
        if (mHasUser) {
            mEtUserName.setText(getUserName());
            if(mRememberPwd){
                mEtUserPwd.setText(getUserPwd());
            }
        }

        mCbRememberPwd.setChecked(mRememberPwd);
        mTvGo.setEnabled(mHasUser && mCbRememberPwd.isChecked());
    }

    @Override
    public void initEvent() {

        /*
        RxJava应用场景：
        CombineLatest操作符可以将2~9个Observable发射的数据组装起来然后再发射出来。不过还有两个前提：
        1.所有的Observable都发射过数据。
        2.满足条件1的时候，任何一个Observable发射一个数据，就将所有Observable最新发射的数据按照提供的函数组装起来发射出去。*/

        Observable<CharSequence> nameObservable = RxTextView.textChanges(mEtUserName);
        Observable<CharSequence> pwdObservable = RxTextView.textChanges(mEtUserPwd);
        Observable.combineLatest(nameObservable, pwdObservable, (name, pwd) -> {

            boolean nameValid = !TextUtils.isEmpty(name);
            if (!nameValid) {
                mEtUserName.setError("我讨厌无名氏");
            }

            boolean pwdValid = !TextUtils.isEmpty(pwd) && pwd.length() < 13 && pwd.length() > 2;

            if (!pwdValid) {
                mEtUserPwd.setError("最少3位，最多12位");
            }

            return nameValid && pwdValid;
        })
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        mTvGo.setEnabled(aBoolean);
                    }
                });

        mTvGo.setOnClickListener((v) -> {

            String name = mEtUserName.getText().toString().trim();
            String pwd = mEtUserPwd.getText().toString().trim();

            if(mHasUser){//已经保存过用户信息
                if(!name.equals(getUserName())){//改了名字（密码改不改都执行）
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("确定更换用户名吗?")
                            .setContentText("换了名字...以前聊天记录就没了")
                            .setConfirmText("换！！！")
                            .setCancelText("我再想想")
                            .setConfirmClickListener((sDialog) -> {

                                //保存用户信息
                                saveUserInfo(name, pwd);

                                //清空原有聊天记录
                                FileUtil.clearLocalChatMsg(getActivity());

                                sDialog.dismissWithAnimation();

                                gotoChat();
                            })
                            .setCancelClickListener((sDialog) -> sDialog.cancel())
                            .show();
                }else {//用户名没改

                    if(getUserPwd().isEmpty()){//重置密码的情况
                        saveUserInfo(name, pwd);
                        gotoChat();
                        return;
                    }

                    if(!pwd.equals(getUserPwd())){//改了密码
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("密码不对呀")
                                .setConfirmText("让我想想")
                                .setCancelText("重置密码")
                                .setConfirmClickListener((sDialog) -> {
                                    sDialog.dismissWithAnimation();
                                    mEtUserPwd.setText("");

                                })
                                .setCancelClickListener((sDialog) -> {
                                    mEtUserPwd.setText("");
                                    sDialog.cancel();
                                    SPUtil.setString(getActivity(), Constants.USER_PWD, "");

                                })
                                .show();
                    }else {//名字和密码都没改动
                        SPUtil.setBoolean(getActivity(), Constants.REMEMBER_PWD, mCbRememberPwd.isChecked());
                        gotoChat();
                    }
                }

            }else {//原来没有保存过用户信息
                saveUserInfo(name, pwd);
                gotoChat();
            }
        });
    }

    private boolean getRememberState(){
        return SPUtil.getBoolean(getActivity(), Constants.REMEMBER_PWD, false);
    }

    private String getUserName(){
        return SPUtil.getString(getActivity(), Constants.USER_NAME, "");

    }

    private String getUserPwd(){
        return SPUtil.getString(getActivity(), Constants.USER_PWD, "");
    }

    private void gotoChat() {
        Intent intent = new Intent(getActivity(), ChatListActivity.class);
        startActivity(intent);
    }

    private void saveUserInfo(String name, String pwd) {
        SPUtil.setString(getActivity(), Constants.USER_NAME, name);
        SPUtil.setString(getActivity(), Constants.USER_PWD, pwd);
        SPUtil.setBoolean(getActivity(), Constants.HAS_USER, true);
        SPUtil.setBoolean(getActivity(), Constants.REMEMBER_PWD, mCbRememberPwd.isChecked());
    }

}
