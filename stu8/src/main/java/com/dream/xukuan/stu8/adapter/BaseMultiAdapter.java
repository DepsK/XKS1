package com.dream.xukuan.stu8.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XK
 * @date 2018/2/26.
 */
public abstract class BaseMultiAdapter<T> extends BaseAdapter {

    protected int[] itemLayoutIds;
    protected Context context;
    protected List<T> datas;
    public BaseMultiAdapter(Context context,int ...itemLayoutIds){
        this.context = context;
        datas = new ArrayList<>();
        this.itemLayoutIds = itemLayoutIds;
    }
    public void addAll(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getType(position,getItem(position));
    }


    protected abstract int getType(int position, T item);

    @Override
    public int getViewTypeCount() {
        return itemLayoutIds.length;
    }

    protected abstract void bindData(ViewHolder viewHolder, T item, int itemViewType);

    protected abstract ViewHolder getViewHolder(int position, View convertView, ViewGroup parent, int itemViewType);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position,convertView,parent,getItemViewType(position));
        bindData(viewHolder,getItem(position),getItemViewType(position));
        return viewHolder.getConverView();
    }
}