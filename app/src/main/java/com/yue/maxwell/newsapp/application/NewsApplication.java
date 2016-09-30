package com.yue.maxwell.newsapp.application;

import android.app.Application;
import android.content.Context;

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

public class NewsApplication extends Application {



    private static Context mContext;

    @Override
    public void onCreate() {

        mContext = getApplicationContext();



        super.onCreate();
    }

    public static Context getContext() {
        return mContext;
    }

}
