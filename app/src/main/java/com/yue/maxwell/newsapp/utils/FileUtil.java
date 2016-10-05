package com.yue.maxwell.newsapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;


import com.jakewharton.disklrucache.DiskLruCache;
import com.yue.maxwell.newsapp.application.NewsApplication;
import com.yue.maxwell.newsapp.bean.ChatMsg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;


/**
 * 2016/10/4 0004，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class FileUtil<T> {

    public List<ChatMsg<T>> loadChatMsgFromLocal(Context context){
        ACache aCache = ACache.get(context);
        return (List<ChatMsg<T>>) aCache.getAsObject("1");
    }

    public void saveChatMsgToLocal(Context context, List<ChatMsg<T>> list){
        ACache aCache = ACache.get(context);
        aCache.put("1", (ArrayList)list);
    }

    public static void clearLocalChatMsg(Context context){
        ACache aCache = ACache.get(context);
        aCache.clear();
    }

}

