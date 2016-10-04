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

public class ChatMenuBean implements Parcelable {

    private int code;
    private String text;
    private List<ListBean> list;

    protected ChatMenuBean(Parcel in) {
        code = in.readInt();
        text = in.readString();
        if(list == null){
            list = new ArrayList<>();
        }
        in.readTypedList(list, ListBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(text);
        dest.writeTypedList(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChatMenuBean> CREATOR = new Creator<ChatMenuBean>() {
        @Override
        public ChatMenuBean createFromParcel(Parcel in) {
            return new ChatMenuBean(in);
        }

        @Override
        public ChatMenuBean[] newArray(int size) {
            return new ChatMenuBean[size];
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    public static class ListBean implements Parcelable {
        private String name;
        private String icon;
        private String info;
        private String detailurl;

        protected ListBean(Parcel in) {
            name = in.readString();
            icon = in.readString();
            info = in.readString();
            detailurl = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(icon);
            dest.writeString(info);
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getDetailurl() {
            return detailurl;
        }

        public void setDetailurl(String detailurl) {
            this.detailurl = detailurl;
        }
    }
}
