package com.yue.maxwell.newsapp.bean;

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

public class BaseResultEntity<T> {

    private int error_code;//0为成功，其他为失败
    private String reason;//失败的信息

    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
/*	错误码	说明	旧版本（resultcode）
 	10001	错误的请求KEY	101
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

}
