package com.yue.maxwell.newsapp.news.presenter;

import com.yue.maxwell.newsapp.base.BaseSubscriber;
import com.yue.maxwell.newsapp.bean.NewsBean;
import com.yue.maxwell.newsapp.common.Constants;
import com.yue.maxwell.newsapp.net.BaseResultFunc;
import com.yue.maxwell.newsapp.net.NetManager;
import com.yue.maxwell.newsapp.news.NewsService;
import com.yue.maxwell.newsapp.news.view.NewsView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

public class NewsPresenterImpl implements NewsPresenter{

    private static final String TAG = NewsPresenterImpl.class.getSimpleName();

    private NewsService mNewsService;
    private NewsView mNewsView;

    public NewsPresenterImpl(NewsView newsView){
        this.mNewsView = newsView;
        this.mNewsService = (NewsService) NetManager.getInstance().create(NewsService.class);
    }

    @Override
    public void loadNews(String type) {

        mNewsView.showProgress();
        mNewsService.getNewsBean(type, Constants.JUHE_APIKEY)
                .map(new BaseResultFunc<NewsBean>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<NewsBean>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        mNewsView.hideProgress();
                        mNewsView.addNews(newsBean.getData());
                    }

                    @Override
                    public void onError(String errorMsg) {
                        mNewsView.hideProgress();
                        mNewsView.showLoadFailMsg(errorMsg);
                    }
                });
    }

}
