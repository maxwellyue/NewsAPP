package com.yue.maxwell.newsapp.news.view;

import com.yue.maxwell.newsapp.bean.NewsBean;

import java.util.List;

/**
 * 2016/9/30 0030，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public interface NewsView {

    void showProgress();

    void addNews(List<NewsBean.SingelNews> newsList);

    void hideProgress();

    void showLoadFailMsg(String errorMsg);
}
