package com.yue.maxwell.newsapp.news;

import com.yue.maxwell.newsapp.bean.BaseResultEntity;
import com.yue.maxwell.newsapp.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 2016/9/29 0029，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public interface NewsService {
    
    /*void LoadNews(String type, NewsModelImpl.OnLoadNewsListListener listener);*/
    @GET("index")
    Observable<BaseResultEntity<NewsBean>> getNewsBean(@Query("type") String type, @Query("key") String key);
}
