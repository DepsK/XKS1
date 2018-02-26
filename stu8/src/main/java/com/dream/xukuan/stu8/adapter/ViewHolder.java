package com.dream.xukuan.stu8.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.xukuan.stu8.R;
import com.dream.xukuan.stu8.util.ImageTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * @author XK
 * @date 2018/2/26.
 */
public class ViewHolder {
    private SparseArray<View> views;
    private Context context;
    private View converView;
    public ViewHolder(Context context, int position, ViewGroup parent, int itemLayoutId){
        this.context = context;
        this.converView = LayoutInflater.from(context).inflate(itemLayoutId,parent,false);
        this.views = new SparseArray<>();
        converView.setTag(this);
    }

    public static ViewHolder getInstance(Context context,int position, View convertView, ViewGroup parent, int itemLayoutId){
        if(convertView == null){
            return new ViewHolder(context,position,parent,itemLayoutId);
        }else {
            return (ViewHolder) convertView.getTag();
        }
    }

    public <T extends View>T getView(int viewId){
        T view = (T) views.get(viewId);
        if(view==null){
            view = (T) converView.findViewById(viewId);
            views.put(viewId,view);
        }
        return view;
    }

    public void setText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setImage(int viewId,String url){
        ImageView imageView = getView(viewId);
        if(url.equals(imageView.getTag())){
            return;
        }
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setTag(url);
        new ImageTask().load(url).into(imageView);
    }

    public View getConverView(){
        return converView;
    }

} 