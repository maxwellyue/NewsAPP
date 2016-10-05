package com.yue.maxwell.newsapp.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.bean.ChatHrefBean;
import com.yue.maxwell.newsapp.bean.ChatMenuBean;
import com.yue.maxwell.newsapp.bean.ChatMsg;
import com.yue.maxwell.newsapp.bean.ChatNewsBean;
import com.yue.maxwell.newsapp.bean.ChatTextBean;
import com.yue.maxwell.newsapp.utils.DateUtil;
import com.yue.maxwell.newsapp.utils.IntentUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

public class ChatListAdapter<T> extends BaseAdapter {

    private Context mContext;

    private List<ChatMsg<T>> mChatMsgList;

    public ChatListAdapter(Context context, List<ChatMsg<T>> chatMsgList) {
        this.mContext = context;
        this.mChatMsgList = chatMsgList;
    }

    /**
     * @return 一共有多少种类型的item
     */
    @Override
    public int getViewTypeCount() {

        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        ChatMsg.Type type = mChatMsgList.get(position).getType();

        if (type == ChatMsg.Type.INPUT) {
            return 0;
        } else if (type == ChatMsg.Type.OUTPUT_TEXT) {
            return 1;
        } else if (type == ChatMsg.Type.OUTPUT_HREF) {
            return 2;
        } else if (type == ChatMsg.Type.OUTPUT_NEWS) {
            return 3;
        } else {
            return 4;
        }

    }

    @Override
    public int getCount() {
        return mChatMsgList.size();
    }

    @Override
    public Object getItem(int i) {
        return mChatMsgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ChatMsg<T> msg = mChatMsgList.get(i);
        ChatMsg.Type type = msg.getType();
        ViewHolderInput inputHolder = null;
        ViewHolderOutputText outputText = null;
        ViewHolderOutputHref outputHref = null;
        ViewHolderOutputNews outputNews = null;
        ViewHolderOutputMenu outputMenu = null;

        if(view == null){
            if (type == ChatMsg.Type.INPUT) {
                view = View.inflate(mContext, R.layout.item_lv_fragment_chat_input, null);
                inputHolder = new ViewHolderInput(view);
                view.setTag(inputHolder);
            } else if (type == ChatMsg.Type.OUTPUT_TEXT) {
                view = View.inflate(mContext, R.layout.item_lv_fragment_chat_output_text, null);
                outputText = new ViewHolderOutputText(view);
                view.setTag(outputText);
            } else if (type == ChatMsg.Type.OUTPUT_HREF) {
                view = View.inflate(mContext, R.layout.item_lv_fragment_chat_output_href, null);
                outputHref = new ViewHolderOutputHref(view);
                view.setTag(outputHref);
            } else if (type == ChatMsg.Type.OUTPUT_NEWS) {
                view = View.inflate(mContext, R.layout.item_lv_fragment_chat_output_news, null);
                outputNews = new ViewHolderOutputNews(view);
                view.setTag(outputNews);
            } else {
                view = View.inflate(mContext, R.layout.item_lv_fragment_chat_output_menu, null);
                outputMenu = new ViewHolderOutputMenu(view);
                view.setTag(outputMenu);
            }
        }else {
            if (type == ChatMsg.Type.INPUT) {
                inputHolder = (ViewHolderInput) view.getTag();
            } else if (type == ChatMsg.Type.OUTPUT_TEXT) {
                outputText = (ViewHolderOutputText) view.getTag();
            } else if (type == ChatMsg.Type.OUTPUT_HREF) {
                outputHref = (ViewHolderOutputHref) view.getTag();
            } else if (type == ChatMsg.Type.OUTPUT_NEWS) {
                outputNews = (ViewHolderOutputNews) view.getTag();
            } else {
                outputMenu = (ViewHolderOutputMenu) view.getTag();
            }
        }


        //赋值
        if (type == ChatMsg.Type.INPUT) {
            inputHolder.content.setText(((ChatTextBean)msg.getMsg()).getText());
            inputHolder.date.setText(DateUtil.getHourAndMinute(new Date()));
        } else if (type == ChatMsg.Type.OUTPUT_TEXT) {
            outputText.content.setText(((ChatTextBean)msg.getMsg()).getText());
        } else if (type == ChatMsg.Type.OUTPUT_HREF) {
            outputHref.content.setText(((ChatHrefBean)msg.getMsg()).getText());
            outputHref.url.setText("戳我查看");
            outputHref.url.setOnClickListener((v) -> {
                IntentUtil.startWebActivity(mContext, ((ChatHrefBean)msg.getMsg()).getUrl());
            });



        } else if (type == ChatMsg.Type.OUTPUT_NEWS) {
            outputNews.content.setText(((ChatNewsBean)msg.getMsg()).getText());

            outputNews.listView.setAdapter(new CommonAdapter<ChatNewsBean.ListBean>(mContext, ((ChatNewsBean)msg.getMsg()).getList(), R.layout.item_item_lv_fragment_chat_news) {
                @Override
                public void setItemViewContent(CommonViewHolder viewHolder, ChatNewsBean.ListBean itemData, int position) {

                    TextView title = viewHolder.getView(R.id.tv_item_item_lv_chat_news_title);
                    TextView source = viewHolder.getView(R.id.tv_item_item_lv_chat_news_source);
                    title.setText(!TextUtils.isEmpty(itemData.getArticle()) ? itemData.getArticle() : "戳我查看");
                    source.setText("(来自:" + itemData.getSource() + ")");

                    title.setOnClickListener((v) ->{
                        IntentUtil.startWebActivity(mContext, itemData.getDetailurl());
                    });
                }
            });
        } else {
            outputMenu.content.setText(((ChatMenuBean)msg.getMsg()).getText());
            outputMenu.listView.setAdapter(new CommonAdapter<ChatMenuBean.ListBean>(mContext, ((ChatMenuBean)msg.getMsg()).getList(), R.layout.item_item_lv_fragment_chat_menu) {
                @Override
                public void setItemViewContent(CommonViewHolder viewHolder, ChatMenuBean.ListBean itemData, int position) {

                    TextView name = viewHolder.getView(R.id.tv_item_item_lv_chat_menu_name);
                    TextView info = viewHolder.getView(R.id.tv_item_item_lv_chat_menu_info);
                    name.setText(!TextUtils.isEmpty(itemData.getName())? itemData.getName() : "戳我查看");
                    info.setText(itemData.getInfo());

                    name.setOnClickListener((v) -> {
                        IntentUtil.startWebActivity(mContext, itemData.getDetailurl());
                    });
                }
            });

        }

        return view;
    }

    static class ViewHolderInput {
        @BindView(R.id.tv_item_lv_fragment_chat_input_content)
        TextView content;
        @BindView(R.id.tv_item_lv_fragment_chat_input_date)
        TextView date;

        public ViewHolderInput(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderOutputText {

        @BindView(R.id.tv_item_lv_fragment_chat_text_content)
        TextView content;


        public ViewHolderOutputText(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderOutputHref {
        @BindView(R.id.tv_item_lv_fragment_chat_href_content)
        TextView content;
        @BindView(R.id.tv_item_lv_fragment_chat_href_url)
        TextView url;

        public ViewHolderOutputHref(View view) {
            ButterKnife.bind(this, view);
        }

    }

    static class ViewHolderOutputNews {
        @BindView(R.id.tv_item_lv_fragment_chat_news_content)
        TextView content;
        @BindView(R.id.lv_item_lv_fragment_chat_news_list)
        ListView listView;

        public ViewHolderOutputNews(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderOutputMenu {
        @BindView(R.id.tv_item_lv_fragment_chat_menu_content)
        TextView content;
        @BindView(R.id.lv_item_lv_fragment_chat_menu_list)
        ListView listView;

        public ViewHolderOutputMenu(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
