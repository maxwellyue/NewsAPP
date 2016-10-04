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

public class FileUtil<T extends Parcelable> {

    public List<ChatMsg<T>> loadChatMsgFromLocal(){
        File file = NewsApplication.getContext().getCacheDir();
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            DiskLruCache cache = DiskLruCache.open(file, 0, 1, 10*1024*1024);
            DiskLruCache.Snapshot snapshot= null;
            snapshot = cache.get("123456");

            InputStream inputStream = snapshot.getInputStream(0);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);

            List<ChatMsg<T>> list = new ArrayList<>();

            // TODO: 2016/10/4 0004 有泛型，不知如何解决
            //parcel.readTypedList(list, ChatMsg.CREATOR);

            inputStream.close();
            snapshot.close();
            parcel.recycle();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveChatMsgToLocal(List<ChatMsg<T>> list){


        File file = NewsApplication.getContext().getCacheDir();
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            DiskLruCache cache = DiskLruCache.open(file, 0, 1, 10*1024*1024);
            DiskLruCache.Editor editor = cache.edit("123456");
            OutputStream outputStream = editor.newOutputStream(0);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            Parcel parcel = Parcel.obtain();
            parcel.writeTypedList(list);
            bos.write(parcel.marshall());
            bos.flush();
            bos.close();
            outputStream.flush();
            outputStream.close();
            editor.commit();
            cache.flush();
            parcel.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

