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

public class ChatHrefBean implements Parcelable {

    /**
     * code : 200000
     * text : 亲，已帮你找到图片
     * url : http://m.image.so.com/i?q=%E5%B0%8F%E7%8B%97
     */

    private int code;
    private String text;
    private String url;

    protected ChatHrefBean(Parcel in) {
        code = in.readInt();
        text = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(text);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatHrefBean> CREATOR = new Creator<ChatHrefBean>() {
        @Override
        public ChatHrefBean createFromParcel(Parcel in) {
            return new ChatHrefBean(in);
        }

        @Override
        public ChatHrefBean[] newArray(int size) {
            return new ChatHrefBean[size];
        }
    };

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
