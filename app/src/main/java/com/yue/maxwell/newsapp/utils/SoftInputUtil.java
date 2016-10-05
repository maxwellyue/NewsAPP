package com.yue.maxwell.newsapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class SoftInputUtil {

    /**
     * 关闭软件盘
     * @param activity
     * @param view 与软键盘相联系的View，这个View要可以获取和失去焦点，一般为EditText
     */
    public static void closeSoftInput(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 打开软件盘
     * @param activity
     * @param view 与软键盘相联系的View，这个View要可以获取和失去焦点，一般为EditText
     */
    public static void openSoftInput(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 开关软件盘
     * 如果输入法在窗口上已经显示，则隐藏;反之则显示
     * @param activity
     */
    public static void toggleSoftInput(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
