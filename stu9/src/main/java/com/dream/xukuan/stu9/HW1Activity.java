package com.dream.xukuan.stu9;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW1Activity extends AppCompatActivity {

    ListView listView;
    ImageView addView;
    RelativeLayout topView;
    SimpleAdapter adapter;
    List<Map<String,Object>> list;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw1);
        list = new ArrayList<>();
        Map map = new HashMap();
        map.put("name","发起群聊");
        map.put("icon",R.drawable.aaaa);
        list.add(map);
        map = new HashMap();
        map.put("name","添加朋友");
        map.put("icon",R.drawable.bb);
        list.add(map);
        map = new HashMap();
        map.put("name","扫一扫");
        map.put("icon",R.drawable.cc);
        list.add(map);
        map = new HashMap();
        map.put("name","收付款");
        map.put("icon",R.drawable.dd);
        list.add(map);
        initViews();
        setPopupWindow();
    }

    private void setPopupWindow() {
        View popupView = LayoutInflater.from(HW1Activity.this).inflate(R.layout.activity_hw1_popup_layout,null);
        listView = (ListView) popupView.findViewById(R.id.hw1_list_view);
        setListView();
        popupWindow = new PopupWindow(popupView, 400,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    popupWindow.showAsDropDown(topView,getResources().getDisplayMetrics().widthPixels-410,0);
                }
            }
        });
    }

    private void setListView() {
        adapter = new SimpleAdapter(HW1Activity.this,list,R.layout.activity_hw1_item_layout,
                new String[]{"icon","name"},new int[]{R.id.icon,R.id.content});
        listView.setAdapter(adapter);
    }

    private void initViews() {
        addView = (ImageView) findViewById(R.id.add);
        topView = (RelativeLayout) findViewById(R.id.top);
    }
}
