package com.yue.maxwell.newsapp.base;

import com.yue.maxwell.newsapp.net.exception.ExceptionEngine;

import rx.Subscriber;

/**
 * 2016/9/30 0030，由 Administrator 创建 .
 * <p>
 * 功能描述：并没有实现，觉得这个思路并不好
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        //showProgressDialog();
    }

    @Override
    public void onCompleted() {
        //dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        onError(ExceptionEngine.handleException(e));
    }

    @Override
    public void onNext(T t) {
        //dismissProgressDialog();
        onSuccess(t);
    }


    public abstract void onSuccess(T t);


    /**
     * 错误回调
     */
    public abstract void onError(String errorMsg);


}
