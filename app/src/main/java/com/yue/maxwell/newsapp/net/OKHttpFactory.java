package com.yue.maxwell.newsapp.net;

import com.yue.maxwell.newsapp.application.NewsApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 2016/9/28 0028，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public enum OKHttpFactory {
    /*使用拦截器实现以下功能：

    1、设置通用Header
    2、设置通用请求参数
    3、拦截响应
    4、统一输出日志
    5、实现缓存

    */

    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OKHttpFactory() {

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(InterceptorFactory.getHttpLoggingInterceptor()) //添加打印请求log的Interceptor
                .cache(new Cache(NewsApplication.getContext().getCacheDir(), 10 * 1024 * 1024))//设置Cache目录
                .addNetworkInterceptor(InterceptorFactory.getCacheInterceptor())//设置缓存拦截器()
                .addInterceptor(InterceptorFactory.getCacheInterceptor())//设置缓存拦截器()
                .retryOnConnectionFailure(true)//失败重连
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)//设置请求超时时间
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)//设置请求超时时间
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
