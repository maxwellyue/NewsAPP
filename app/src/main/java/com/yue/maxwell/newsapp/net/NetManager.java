package com.yue.maxwell.newsapp.net;

import com.yue.maxwell.newsapp.application.NewsApplication;
import com.yue.maxwell.newsapp.common.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

public class NetManager<T> {

    private Retrofit retrofit;
    OkHttpClient okHttpClient;
    private static final NetManager instance = new NetManager();

    private NetManager() {
    }

    public static NetManager getInstance() {
        return instance;
    }

    public T create(Class clazz) {
        retrofit = new Retrofit.Builder()
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                .baseUrl(Constants.BASE_URL_JUHE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return (T) retrofit.create(clazz);
    }


    public T createString(Class clazz) {
       okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(InterceptorFactory.getHttpLoggingInterceptor()) //添加打印请求log的Interceptor
                //.cache(new Cache(NewsApplication.getContext().getCacheDir(), 10 * 1024 * 1024))//设置Cache目录
                //.addNetworkInterceptor(InterceptorFactory.getCacheInterceptor())//设置缓存拦截器()
                //.addInterceptor(InterceptorFactory.getCacheInterceptor())//设置缓存拦截器()
                .retryOnConnectionFailure(true)//失败重连
                .readTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
                .build();



        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL_TULING)
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return (T) retrofit.create(clazz);
    }

}
