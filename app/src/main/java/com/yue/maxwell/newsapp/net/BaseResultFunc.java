package com.yue.maxwell.newsapp.net;

import com.yue.maxwell.newsapp.bean.BaseResultEntity;
import com.yue.maxwell.newsapp.net.exception.ServerException;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class BaseResultFunc<T> implements Func1<BaseResultEntity<T>, T> {

    @Override
    public T call(BaseResultEntity<T> result) {
        if (result.getError_code() != 0) {
            throw new ServerException(result.getError_code());
        }
        return  result.getResult();
    }
}