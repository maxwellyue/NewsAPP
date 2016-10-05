package com.yue.maxwell.newsapp.utils;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * 2016/10/5 0005，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class DeviceUtil {
    private static final String TAG = "手机信息";
    /**
     * 获取应用程序的IMEI号
     */
    public static String getIMEI(Context context) {
        if (context == null) {
            Log.e(TAG,"getIMEI  context为空");
        }
        TelephonyManager telecomManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telecomManager.getDeviceId();
        Log.e(TAG,"IMEI标识：" + imei);
        return imei;
    }

    /**
     * 获取设备的系统版本号
     */
    public static int getDeviceSDK() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        Log.e(TAG,"设备版本：" + sdk);
        return sdk;
    }

    /**
     * 获取设备的型号
     */
    public static String getDeviceName() {
        String model = android.os.Build.MODEL;
        Log.e(TAG,"设备型号：" + model);
        return model;
    }
}
