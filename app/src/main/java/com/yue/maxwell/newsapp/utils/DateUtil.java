package com.yue.maxwell.newsapp.utils;

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

public class DateUtil {


    public static String getFormatDate(Date date){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String getHourAndMinute(Date date){

        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(date);
    }


}
