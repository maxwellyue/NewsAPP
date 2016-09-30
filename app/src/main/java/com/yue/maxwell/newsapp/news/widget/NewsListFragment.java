package com.yue.maxwell.newsapp.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.adapter.NewsListAdapter;
import com.yue.maxwell.newsapp.bean.NewsBean;
import com.yue.maxwell.newsapp.common.NewsType;
import com.yue.maxwell.newsapp.base.BaseFragment;
import com.yue.maxwell.newsapp.news.presenter.NewsPresenter;
import com.yue.maxwell.newsapp.news.presenter.NewsPresenterImpl;
import com.yue.maxwell.newsapp.news.view.NewsView;
import com.yue.maxwell.newsapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

public class NewsListFragment extends BaseFragment implements NewsView {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    @BindView(R.id.srf_fragment_technews)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.lv_fragment_technews)
    ListView mListView;

    private int mNewsType;

    private List<NewsBean.SingelNews> mNewsList = new ArrayList<>();
    private NewsListAdapter mAdapter;
    private NewsPresenter mNewsPresenter;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(this);
        mNewsType = getArguments().getInt("type");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_common_news;
    }


    @Override
    public void initData() {
        mAdapter = new NewsListAdapter(getActivity(), mNewsList, R.layout.item_lv_fragment_news);
        mListView.setAdapter(mAdapter);
        mNewsPresenter.loadNews(getNewsType(mNewsType));
    }

    public String getNewsType(int type){
        switch (type){
            case NewsFragment.FRAGMENT_CAIJING: return NewsType.CAIJING.toString();
            case NewsFragment.FRAGMENT_GUOJI: return NewsType.GUOJI.toString();
            case NewsFragment.FRAGMENT_GUONEI: return NewsType.GUONEI.toString();
            case NewsFragment.FRAGMENT_JUNSHI: return NewsType.JUNSHI.toString();
            case NewsFragment.FRAGMENT_SHEHUI: return NewsType.SHEHUI.toString();
            case NewsFragment.FRAGMENT_SHISHANG: return NewsType.SHISHANG.toString();
            case NewsFragment.FRAGMENT_SPORTNEWS: return NewsType.TIYU.toString();
            case NewsFragment.FRAGMENT_TECHNEWS: return NewsType.KEJI.toString();
            case NewsFragment.FRAGMENT_TOUTIAO: return NewsType.TOP.toString();
            case NewsFragment.FRAGMENT_YULE: return NewsType.YULE.toString();
            default: return null;
        }
    }

    @Override
    public void initEvent() {

        mSwipeLayout.setOnRefreshListener(() -> {
            mSwipeLayout.setRefreshing(true);
            mNewsPresenter.loadNews(getNewsType(mNewsType));

        });

        mListView.setOnItemClickListener((adapterView, view, i, l) ->{
            Intent intent = new Intent(getActivity(), NewsContentActivity.class);
            intent.putExtra("NEWS_URL", mNewsList.get(i).getUrl());
            startActivity(intent);
        });

    }

    @Override
    public void showProgress() {

        mSwipeLayout.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean.SingelNews> newsList) {

        mNewsList.clear();
        mNewsList.addAll(newsList);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void hideProgress() {
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg(String errorMsg) {
        ToastUtil.showShort(errorMsg);
    }
}
