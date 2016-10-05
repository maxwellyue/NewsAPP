package com.yue.maxwell.newsapp.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 2016/9/23 0023，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //加载布局
        initView();

        //加载数据
        initData();

        //事件监听
        initEvent();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //这里主要是防止Fragment重叠，不去保存。。但一般还是处理一下，不要采取这种方式来避免fragment重叠
        //super.onSaveInstanceState(outState);
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initEvent();

}
