package com.yue.maxwell.newsapp.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

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

public class ChatMsg<T> implements Serializable{

    private static final long serialVersionUID = 2645013734898809713L;
    /**
     * 消息类型
     */
    private Type mType;
    /**
     * 消息内容
     */
    private T msg;

    /**
     * 日期的字符串格式
     */
    private String dateStr;

    public ChatMsg(){}

    public ChatMsg(Type type, T msg){
        this.mType = type;
        this.msg = msg;
        this.dateStr = setDate(new Date());
    }

    private String setDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public String getDateStr() {
        return dateStr;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        this.mType = type;
    }


    public enum Type
    {
        INPUT, OUTPUT_TEXT, OUTPUT_HREF, OUTPUT_MENU, OUTPUT_NEWS
    }






}
