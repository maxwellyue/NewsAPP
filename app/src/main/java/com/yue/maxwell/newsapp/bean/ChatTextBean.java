package com.yue.maxwell.newsapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2016/10/3 0003，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ChatTextBean implements Parcelable {

    /**
     * code : 100000
     * text : 你也好 嘻嘻
     */

    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ChatTextBean(){}

    //=================================序列化相关=================================

    public static final Creator<ChatTextBean> CREATOR = new Creator<ChatTextBean>() {
        @Override
        public ChatTextBean createFromParcel(Parcel in) {
            return new ChatTextBean(in);
        }

        @Override
        public ChatTextBean[] newArray(int size) {
            return new ChatTextBean[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(text);
    }

    public ChatTextBean(Parcel in) {
        code = in.readInt();
        text = in.readString();
    }


}
