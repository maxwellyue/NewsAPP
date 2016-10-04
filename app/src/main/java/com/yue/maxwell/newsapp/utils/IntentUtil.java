package com.yue.maxwell.newsapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class IntentUtil {


    /**
     * 直接拨号，需要增加CALL_PHONE权限
     *
     * @param context 上下文(一般是Activity)
     * @param phone   手机号码
     */
    public static void startCallActivity(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        intent.setAction(Intent.ACTION_CALL);// 直接拨号
        context.startActivity(intent);
    }


    /**
     * 跳到拨号盘-拨打电话
     *
     * @param context 上下文
     * @param phone   手机号码
     */
    public static void startDialActivity(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        intent.setAction(Intent.ACTION_DIAL);// 拨号盘
        context.startActivity(intent);
    }

    public static void startSmsActivity(Context context, String phoneNumber) {
        try {
            Uri uri = Uri.parse("sms:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra("address", phoneNumber);
            intent.setType("vnd.android-dir/mms-sms");
            context.startActivity(intent);
        } catch (Exception ex) {

            Toast.makeText(context,
                    "Sorry, we couldn't find any app to send an SMS!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void startEmailActivity(Context context, String emailAddress) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{emailAddress});
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context,
                    "Sorry, we couldn't find any app for sending emails!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void startEmailActivity(Context context, String to, String subject, String body) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");

        if (!TextUtils.isEmpty(to)) {
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        }
        if (!TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!TextUtils.isEmpty(body)) {
            intent.putExtra(Intent.EXTRA_TEXT, body);
        }

        final PackageManager pm = (PackageManager) context.getPackageManager();
        try {
            if (pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() == 0) {
                intent.setType("text/plain");
            }
        }
        catch (Exception e) {
            Toast.makeText(context,
                    "Exception encountered while looking for email intent receiver.",
                    Toast.LENGTH_SHORT).show();
        }

        context.startActivity(intent);
    }





    public static void startWebActivity(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(context,
                    "Sorry, we couldn't find any app for viewing this url!",
                    Toast.LENGTH_SHORT).show();
        }
    }






}
