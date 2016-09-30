package com.yue.maxwell.newsapp.net.exception;

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

public class ServerException extends RuntimeException{

    /* 10001	错误的请求KEY	101
             10002	该KEY无请求权限	102
             10003	KEY过期	103
             10004	错误的OPENID	104
             10005	应用未审核超时，请提交认证	105
             10007	未知的请求源	107
             10008	被禁止的IP	108
             10009	被禁止的KEY	109
             10011	当前IP请求超过限制	111
             10012	请求超过次数限制	112
             10013	测试KEY超过请求限制	113
             10014	系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)	114
             10020	接口维护	120
             10021	接口停用	121*/

    public int getCode() {
        return code;
    }

    private int code;
    private String msg;

    public ServerException(int resultCode){
        this.code = resultCode;
    }

    public  String getDetailMessage(int code){
        String message = "";
        switch (code) {
            case 10001:
                message = "错误的请求KEY";
                break;
            case 10002:
                message = "该KEY无请求权限";
                break;
            case 10003:
                message = "KEY过期";
                break;
            case 10004:
                message = "错误的OPENID";
                break;
            case 10005:
                message = "应用未审核超时，请提交认证";
                break;
            case 10007:
                message = "未知的请求源";
                break;
            case 10008:
                message = "被禁止的IP";
                break;
            case 10009:
                message = "被禁止的KEY";
                break;
            case 100011:
                message = "当前IP请求超过限制";
                break;
            case 100012:
                message = "请求超过次数限制";
                break;
            case 100013:
                message = "测试KEY超过请求限制";
                break;
            case 100014:
                message = "系统内部异常";
                break;
            case 100020:
                message = "接口维护";
                break;
            case 100021:
                message = "接口停用";
                break;
            default:
                message = "error";
                break;

        }
        return message;
    }



}
