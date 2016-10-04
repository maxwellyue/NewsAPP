package com.yue.maxwell.newsapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

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

public class ChatNewsBean implements Parcelable {


    private int code;
    private String text;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    protected ChatNewsBean(Parcel in) {
        code = in.readInt();
        text = in.readString();
        if(list == null){
            list = new ArrayList<>();
        }
        in.readTypedList(list, ListBean.CREATOR);

    }

    public static final Creator<ChatNewsBean> CREATOR = new Creator<ChatNewsBean>() {
        @Override
        public ChatNewsBean createFromParcel(Parcel in) {
            return new ChatNewsBean(in);
        }

        @Override
        public ChatNewsBean[] newArray(int size) {
            return new ChatNewsBean[size];
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
        parcel.writeTypedList(list);

    }






    public static class ListBean implements Parcelable {
        private String article;
        private String source;
        private String icon;
        private String detailurl;

        protected ListBean(Parcel in) {
            article = in.readString();
            source = in.readString();
            icon = in.readString();
            detailurl = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(article);
            dest.writeString(source);
            dest.writeString(icon);
            dest.writeString(detailurl);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDetailurl() {
            return detailurl;
        }

        public void setDetailurl(String detailurl) {
            this.detailurl = detailurl;
        }
    }
}
