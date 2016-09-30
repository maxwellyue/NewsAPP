package com.yue.maxwell.newsapp.main;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.base.BaseActivity;
import com.yue.maxwell.newsapp.joke.JokeFragment;
import com.yue.maxwell.newsapp.news.widget.NewsFragment;
import com.yue.maxwell.newsapp.tool.ToolFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private NewsFragment mNewsFragment;
    private JokeFragment mSportNewsFragment;
    private ToolFragment mVedioNewsFragment;

    @BindView(R.id.toolbar_activity_main)
    Toolbar mToolbar;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initEvent() {
        mBottomBar.setOnTabSelectListener((@IdRes int tabId) -> {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragments(transaction);
                //调用show() & hide()方法时，Fragment的生命周期方法并不会被执行
                switch (tabId){
                    case R.id.tab_news:
                        if(mNewsFragment == null){
                            mNewsFragment = new NewsFragment();
                            transaction.add(R.id.fl_activity_main_content, mNewsFragment);
                        }
                        transaction.show(mNewsFragment);
                        break;
                    case R.id.tab_joke:
                        if(mSportNewsFragment == null){
                            mSportNewsFragment = new JokeFragment();
                            transaction.add(R.id.fl_activity_main_content, mSportNewsFragment);
                        }
                        transaction.show(mSportNewsFragment);
                        break;
                    case R.id.tab_tool:
                        if(mVedioNewsFragment == null){
                            mVedioNewsFragment = new ToolFragment();
                            transaction.add(R.id.fl_activity_main_content, mVedioNewsFragment);
                        }
                        transaction.show(mVedioNewsFragment);
                        break;
                    default:
                        break;
                }

                transaction.commit();
            });

    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (mNewsFragment != null) {
            transaction.hide(mNewsFragment);
        }
        if (mSportNewsFragment != null) {
            transaction.hide(mSportNewsFragment);
        }
        if (mVedioNewsFragment != null) {
            transaction.hide(mVedioNewsFragment);
        }
    }


}


