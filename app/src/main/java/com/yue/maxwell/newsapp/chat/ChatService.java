package com.yue.maxwell.newsapp.chat;

import com.yue.maxwell.newsapp.bean.TulingApiInfoBean;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 2016/10/3 0003，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public interface ChatService {

    @POST("api")
    Observable<String> chatMsg(@Body RequestBody apiInfo);
}
