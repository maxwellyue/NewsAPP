package com.yue.maxwell.newsapp.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yue.maxwell.newsapp.R;
import com.yue.maxwell.newsapp.bean.NewsBean;

import java.util.List;

/**
 * 2016/9/26 0026，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

public class NewsListAdapter extends CommonAdapter<NewsBean.SingelNews> {

    private Context mContext;

    public NewsListAdapter(Context context, List<NewsBean.SingelNews> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        mContext = context;
    }

    @Override
    public void setItemViewContent(CommonViewHolder viewHolder, NewsBean.SingelNews itemData, int position) {

        TextView title = viewHolder.getView(R.id.tv_item_lv_fragment_news_title);
        TextView source = viewHolder.getView(R.id.tv_item_lv_fragment_news_source);
        TextView time = viewHolder.getView(R.id.tv_item_lv_fragment_news_time);
        ImageView image1 = viewHolder.getView(R.id.iv_item_lv_fragment_news_pic1);
        ImageView image2 = viewHolder.getView(R.id.iv_item_lv_fragment_news_pic2);
        ImageView image3 = viewHolder.getView(R.id.iv_item_lv_fragment_news_pic3);


        Glide.with(mContext).load(itemData.getThumbnail_pic_s()).into(image1);
        Glide.with(mContext).load(itemData.getThumbnail_pic_s02()).into(image2);
        Glide.with(mContext).load(itemData.getThumbnail_pic_s03()).into(image3);
        title.setText(itemData.getTitle());
        source.setText(itemData.getAuthor_name());
        time.setText(itemData.getDate());

    }
}
