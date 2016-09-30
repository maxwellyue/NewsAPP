package com.yue.maxwell.newsapp.common;

/**
 * 2016/9/29 0029，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public enum NewsType {

  /*  类型,,top(头条，默认),shehui(社会),guonei(国内),
    guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),
    keji(科技),caijing(财经),shishang(时尚)*/


    TOP("top"), SHEHUI("shehui"), GUONEI("guonei"), GUOJI("guoji"),
    YULE("yule"), TIYU("tiyu"), JUNSHI("junshi"),
    KEJI("keji"), CAIJING("caijing"), SHISHANG("shishang");

    private String type;
    private NewsType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
