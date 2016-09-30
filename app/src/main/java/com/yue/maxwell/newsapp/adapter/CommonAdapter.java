package com.yue.maxwell.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的Adapter，一般的Adapter只要继承CommonAdapter，仅仅实现setItemViewContent即可
 *
 *
 *
 * Created by Administrator on 2016/8/23 0023.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount()
    {
        return mDatas != null && mDatas.size() > 0 ? mDatas.size() : 0;
    }

    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        CommonViewHolder viewHolder
                = CommonViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
        setItemViewContent(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    /**
     * 给item中的各种控件设置数据内容
     *
     * @param viewHolder 通过viewHolder.getView（）根据子控件的id来获取子控件
     * @param itemData 该position处的数据，一般是Bean实体类
     * @param position 位置
     */
    public abstract void setItemViewContent(CommonViewHolder viewHolder, T itemData, int position);


//--------------------------使用示例--------------------------------

}
