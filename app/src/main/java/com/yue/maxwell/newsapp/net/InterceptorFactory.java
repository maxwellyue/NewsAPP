package com.yue.maxwell.newsapp.net;

import com.yue.maxwell.newsapp.net.exception.ApiException;
import com.yue.maxwell.newsapp.net.exception.ErrorType;
import com.yue.maxwell.newsapp.utils.NetConnectUtil;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

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

public class InterceptorFactory {

    /**
     * 设置通用Header
     *
     * @return
     */
    public static Interceptor getRequestHeader() {

        Interceptor headerInterceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                //builder.header("appid", "1");
                //builder.header("timestamp", System.currentTimeMillis() + "");
                //builder.header("appkey", "zRc9bBpQvZYmpqkwOo");
                //builder.header("signature", "dsljdljflajsnxdsd");

                Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        return headerInterceptor;
    }

    /**
     * 统一输出请求日志
     *
     * @return
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


    /**
     * 拦截服务器响应
     *
     * @return
     */
    public static Interceptor getResponseHeader() {

        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                String timestamp = response.header("time");
                if (timestamp != null) {
                    //获取到响应header中的time
                }
                return response;
            }
        };
        return interceptor;
    }

    /**
     * 缓存拦截器
     * 策略：在无网络的情况下读取缓存，有网络的情况下根据缓存的过期时间重新请求
     *
     * @return
     */
    public static Interceptor getCacheInterceptor() {

        return chain ->{
                Request request = chain.request();
                if (!NetConnectUtil.isNetConnected()) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetConnectUtil.isNetConnected()) {//有网络情况下，根据请求接口的设置，配置缓存。
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间置为0即可: String cacheControl="Cache-Control:public,max-age=0"
                    return response.newBuilder().header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {//无网络

                    return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .removeHeader("Pragma")
                            .build();

                }

            };
    }






}
