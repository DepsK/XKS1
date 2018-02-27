package com.dream.xukuan.stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FifthActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    int position;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        listView = (ListView) findViewById(R.id.list);
        list.add("长按可以为题问题");
        list.add("是大额我让他");
        list.add("委托权");
        list.add("司法哈");
        list.add("问题请问");
        adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        ImageView imageView = (ImageView) findViewById(R.id.more_view);
        final PopupMenu popupMenu = new PopupMenu(this,imageView);
        popupMenu.inflate(R.menu.more_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_item:{
                        list.add("增加数据了");
                        adapter.notifyDataSetChanged();
                    }
                    break;
                    case R.id.call_item:{
                        Toast.makeText(FifthActivity.this,"呼叫。。。。。。",Toast.LENGTH_LONG).show();
                    }
                    break;
                    case R.id.search_item:{
                        Toast.makeText(FifthActivity.this,"搜索。。。。。。",Toast.LENGTH_LONG).show();

                    }
                    break;
                    default:
                }
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }
}
