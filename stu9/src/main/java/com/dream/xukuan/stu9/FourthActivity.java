package com.dream.xukuan.stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    int position;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        listView = (ListView) findViewById(R.id.list);
        list.add("长按可以为题问题");
        list.add("是大额我让他");
        list.add("委托权");
        list.add("司法哈");
        list.add("问题请问");
        adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        View headView = LayoutInflater.from(this).inflate(R.layout.head_layout,null);
        menu.setHeaderView(headView);
        menu.add(0,1,1,"删除");
        menu.add(0,2,2,"修改");
        //获得被长按的item的位置
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:{
                //删除
                list.remove(position);
                adapter.notifyDataSetChanged();

            }
            break;
            case 2:{
                list.set(position,"修改数据了！");
                adapter.notifyDataSetChanged();
            }
        }
        return true;
    }
}
