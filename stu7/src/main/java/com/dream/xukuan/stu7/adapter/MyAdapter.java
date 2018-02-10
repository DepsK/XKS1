package com.dream.xukuan.stu7.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.xukuan.stu7.MainActivity;
import com.dream.xukuan.stu7.R;
import com.dream.xukuan.stu7.bean.MyNewsEntity;
import com.dream.xukuan.stu7.util.MyImageTask;

import java.util.List;

/**
 * @author XK
 * @date 2018/2/9.
 */
public class MyAdapter extends BaseAdapter {

    private int itemLayout;
    private Context context;
    private List<MyNewsEntity> entityList;

    public MyAdapter(Context context, int itemLayout, List<MyNewsEntity> entityList) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.entityList = entityList;
    }

    @Override
    public int getCount() {
        return entityList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
            viewHolder = new ViewHolder();
             viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title);
            viewHolder.descripTextView = (TextView) convertView.findViewById(R.id.content);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        viewHolder.titleTextView .setText(entityList.get(position).getTitle());
        viewHolder.descripTextView .setText(entityList.get(position).getContent());

        String imgUrlString = "http://tnfs.tngou.net/img"+entityList.get(position).getImageUrl();
        //开启异步任务，下载图片
        ImageView imageView = viewHolder.imageView;

        //如果这次要下载图片和复用的imageView的之前图片不是同一张。
        if (!imgUrlString.equals(imageView.getTag())) {
            //防止短时间的图片错位：如果这次的子视图是复用的，上一次加载图片，并不会被清除，这一次的下载又需要一段时间，
            //复用的时候，清除上一次加载的图片
            imageView.setImageResource(R.mipmap.ic_launcher);

            //如果上次的图片的下载任务在设置默认图的时候还没有完成，任然会错位，为了防止这种情况，需要记录这个imageView这一次应该显示的图片的url
            imageView.setTag(imgUrlString);

            MyImageTask task = new MyImageTask(imageView);
            task.execute(imgUrlString);
        }else {
            Log.e("print", "复用的刚好是需要的图片。。。。");
        }
        return convertView;
    }

    public void addAll(List<MyNewsEntity> list) {
        list.addAll(entityList);
        notifyDataSetChanged();

    }

    class ViewHolder {
        TextView titleTextView,descripTextView;
        ImageView imageView;
    }
}