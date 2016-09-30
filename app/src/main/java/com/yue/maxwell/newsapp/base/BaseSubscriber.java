package com.yue.maxwell.newsapp.base;

import com.yue.maxwell.newsapp.net.exception.ApiException;
import com.yue.maxwell.newsapp.net.exception.ErrorType;
import com.yue.maxwell.newsapp.net.exception.ExceptionEngine;
import com.yue.maxwell.newsapp.utils.NetConnectUtil;
import com.yue.maxwell.newsapp.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 2016/9/28 0028，由 Administrator 创建 .
 * <p>
 * 功能描述：主要是统一处理异常
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {

    }

    @Override
    public void onError(Throwable e) {
        //if(e instanceof ApiException){
            onError(ExceptionEngine.handleException(e));
        //}
    }

    /**
     * 错误回调
     */
    public abstract void onError(String errorMsg);

}
