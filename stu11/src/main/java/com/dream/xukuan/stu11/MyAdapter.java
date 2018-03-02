package com.dream.xukuan.stu11;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * @author XK
 * @date 2018/3/2.
 */
public class MyAdapter extends CursorAdapter {


    public MyAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_hw_item_laytout, null);
        return view;
    }

    /**
     * @param view    就是子视图对象
     * @param context 上下文
     * @param cursor  数据：cursor已经自动移动到当前子视图的位置
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameText = (TextView) view.findViewById(R.id.hw_name);
        TextView ageText = (TextView) view.findViewById(R.id.hw_age);
        TextView sexText = (TextView) view.findViewById(R.id.hw_sex);
        nameText.setText(cursor.getColumnIndex("name"));
        ageText.setText(cursor.getColumnIndex("age"));
        sexText.setText(cursor.getColumnIndex("sex"));
    }
}