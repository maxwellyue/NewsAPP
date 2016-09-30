package com.yue.maxwell.newsapp.net.exception;

import java.util.Stack;

/**
 * 2016/9/28 0028，由 Administrator 创建 .
 * <p>
 * 功能描述：对Exception的统一处理
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class ApiException extends RuntimeException{

    private int code;

    //用于展示的异常信息
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }


}
