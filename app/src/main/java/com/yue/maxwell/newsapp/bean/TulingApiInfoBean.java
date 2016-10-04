package com.yue.maxwell.newsapp.bean;

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

public class TulingApiInfoBean {
/*

    “key”: “APIKEY”,


            “info”: “今天天气怎么样”,


            “loc”:“北京市中关村”,


            “userid”:“123456”
*/
    private String key;
    private String info;
    private String loc;
    private String userid;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
