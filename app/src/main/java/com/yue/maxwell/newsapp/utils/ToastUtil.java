package com.yue.maxwell.newsapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.yue.maxwell.newsapp.application.NewsApplication;


/**
 * Created by yuezengcun on 16/5/13.
 */
public class ToastUtil {

    private static Toast toast;

    public static void show(Context mContext, String text, boolean shortShow) {
        if(toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(mContext, text, shortShow? Toast.LENGTH_SHORT: Toast.LENGTH_LONG);
        toast.show();
    }

    public static void show(Context mContext, String text, boolean shortShow, int gravity) {
        if(toast!=null){
            toast.cancel();
        }
        toast = Toast.makeText(mContext, text, shortShow? Toast.LENGTH_SHORT: Toast.LENGTH_LONG);
        toast.setGravity(gravity,0,0);
        toast.show();
    }

    public static void show(Context mContext, int resId, boolean shortShow) {
        String text = mContext.getString(resId);
        show(mContext, text, shortShow);
    }

    public static void show(int resId, boolean shortShow){
        show(NewsApplication.getContext(), resId, shortShow);
    }

    public static void show(String text, boolean shortShow){
        show(NewsApplication.getContext(), text, shortShow);
    }

    public static void showShort(String text){
        show(text, true);
    }

    public static void showLong(String text){
        show(text, false);
    }

}
