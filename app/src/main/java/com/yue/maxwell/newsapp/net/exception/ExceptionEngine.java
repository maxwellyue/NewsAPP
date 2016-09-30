package com.yue.maxwell.newsapp.net.exception;

import com.google.gson.JsonParseException;
import com.yue.maxwell.newsapp.utils.NetConnectUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * 2016/9/28 0028，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ExceptionEngine {


    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static String handleException(Throwable e) {

        ApiException ex;
/*
        if (!NetConnectUtil.isNetConnected()) {
            ex = new ApiException(e, ErrorType.NO_NETWORK);
            ex.setDisplayMessage("网络不可用");

        } else */if (e instanceof HttpException) {//HTTP错误

            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ErrorType.HTTP_ERROR);
            ex.setDisplayMessage("网络错误");//均视为网络错误
           /*
           也可以把信息设置的详细一些
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("网络错误");
                    break;
            }*/


        } else if (e instanceof ServerException) {//服务器返回的错误

            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, ErrorType.SERVER_ERROR);
            ex.setDisplayMessage(resultException.getDetailMessage(((ServerException) e).getCode()));

        } else if (e instanceof JsonParseException//解析错误
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ErrorType.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");

        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            ex = new ApiException(e, ErrorType.NETWORD_ERROR);
            ex.setDisplayMessage("连接失败");  //均视为网络错误

        } else {
            ex = new ApiException(e, ErrorType.UNKNOWN);
            ex.setDisplayMessage("未知错误");          //未知错误

        }

        return ex.getDisplayMessage();
    }

}
