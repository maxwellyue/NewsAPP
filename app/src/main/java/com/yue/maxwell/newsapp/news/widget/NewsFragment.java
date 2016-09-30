package com.yue.maxwell.newsapp.news.widget;

import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStripExtends;
import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.adapter.NewsFragmentAdapter;
import com.yue.maxwell.newsapp.base.BaseFragment;

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

public class NewsFragment extends BaseFragment {

    @BindView(R.id.tab_fragment_news)
    PagerSlidingTabStripExtends mTab;
    @BindView(R.id.vp_fragment_news)
    ViewPager mViewPager;

    public static final int FRAGMENT_TECHNEWS = 0;
    public static final int FRAGMENT_SPORTNEWS = 1;
    public static final int FRAGMENT_SHEHUI = 2;
    public static final int FRAGMENT_GUONEI = 3;
    public static final int FRAGMENT_GUOJI = 4;
    public static final int FRAGMENT_SHISHANG = 5;
    public static final int FRAGMENT_YULE = 6;
    public static final int FRAGMENT_CAIJING = 7;
    public static final int FRAGMENT_JUNSHI = 8;
    public static final int FRAGMENT_TOUTIAO = 9;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initData() {

        setFragmentAdapter();
        mTab.setViewPager(mViewPager);

    }

    private void setFragmentAdapter() {
        NewsFragmentAdapter adapter = new NewsFragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_TOUTIAO), "头条");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_TECHNEWS), "科技");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_SPORTNEWS), "体育");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_GUOJI), "国际");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_GUONEI), "国内");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_YULE), "娱乐");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_JUNSHI), "军事");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_SHEHUI), "社会");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_SHISHANG), "时尚");
        adapter.addFragment(NewsListFragment.newInstance(FRAGMENT_CAIJING), "财经");
        mViewPager.setAdapter(adapter);
    }
}
