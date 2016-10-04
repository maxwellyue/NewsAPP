package com.yue.maxwell.newsapp.bean;

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

public class ChatMenuBean {

    /**
     * code : 308000
     * text : 亲，已帮您找到菜谱信息
     * list : [{"name":"鱼香肉丝","icon":"http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg","info":"猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐","detailurl":"http://m.xiachufang.com/recipe/264781/"}]
     */

    private int code;
    private String text;
    /**
     * name : 鱼香肉丝
     * icon : http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg
     * info : 猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐
     * detailurl : http://m.xiachufang.com/recipe/264781/
     */

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

    public static class ListBean {
        private String name;
        private String icon;
        private String info;
        private String detailurl;

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
