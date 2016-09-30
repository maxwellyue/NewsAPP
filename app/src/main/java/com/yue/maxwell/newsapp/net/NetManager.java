package com.yue.maxwell.newsapp.net;

import com.yue.maxwell.newsapp.bean.BaseResultEntity;
import com.yue.maxwell.newsapp.bean.NewsBean;
import com.yue.maxwell.newsapp.common.Constants;
import com.yue.maxwell.newsapp.base.BaseSubscriber;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    private static final NetManager instance = new NetManager();
    private NetManager(){}
    public static NetManager getInstance(){
        return instance;
    }

    public T create(Class clazz){
        retrofit = new Retrofit.Builder()
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return (T)retrofit.create(clazz);
    }


}
