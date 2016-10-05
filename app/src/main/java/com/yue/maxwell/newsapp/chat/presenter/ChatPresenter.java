package com.yue.maxwell.newsapp.chat.presenter;

import android.content.Context;

import com.yue.maxwell.newsapp.bean.ChatMsg;

import java.util.List;

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

public interface ChatPresenter<T> {
    void getReplyMsg(String info);
    void loadLocalData(Context context);
    void saveMsgToLocal(Context context, List<ChatMsg<T>> msgList);
    void clearMsg(Context context);
}
