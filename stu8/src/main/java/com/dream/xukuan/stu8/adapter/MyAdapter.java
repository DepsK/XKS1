package com.dream.xukuan.stu8.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dream.xukuan.stu8.R;
import com.dream.xukuan.stu8.bean.CityEntity;

import java.util.List;

/**
 * @author XK
 * @date 2018/2/23.
 */
public class MyAdapter extends BaseAdapter{

    private int itemLayout;
    private Context context;
    private List<CityEntity> cityEntityList;

    public MyAdapter(Context context, int itemLayout, List<CityEntity> cityEntityList) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.cityEntityList = cityEntityList;
    }


    @Override
    public int getCount() {
        return cityEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =ViewHolder.getInstance(context,position,convertView,parent,itemLayout);
        viewHolder.setText(R.id.name,cityEntityList.get(position).getName());
        viewHolder.setText(R.id.address,cityEntityList.get(position).getAddress());
        viewHolder.setText(R.id.price_pre,cityEntityList.get(position).getPrice_pre());
        viewHolder.setText(R.id.price_unit,cityEntityList.get(position).getPrice_unit());
        viewHolder.setText(R.id.price_value,cityEntityList.get(position).getPrice_value());
        viewHolder.setImage(R.id.houseImage,cityEntityList.get(position).getImageUrl());
        return viewHolder.getConverView();
    }
    public void addAll(List<CityEntity> list) {
        list.addAll(cityEntityList);
        notifyDataSetChanged();

    }
}