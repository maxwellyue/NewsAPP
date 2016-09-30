package com.yue.maxwell.newsapp.news.widget;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 2016/9/26 0026，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class NewsContentActivity extends BaseActivity {

    @BindView(R .id.webview_activity_newscontent)
    WebView mWebView;

    @Override
    public void initView() {

        setContentView(R.layout.activity_newscontent);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

        String url = getIntent().getStringExtra("NEWS_URL");
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onBackPressed() {
        if(mWebView != null && mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }

    }
}
