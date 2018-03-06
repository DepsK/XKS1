package com.dream.xukuan.stu12hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    String[] titles = {"查询媒体库","查询短信息","查询通话记录","查询系统联系人"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,titles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(MainActivity.this,MediaActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 1:{
                        Intent intent = new Intent(MainActivity.this,SmsActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 2:{
                        Intent intent = new Intent(MainActivity.this,TellogActivity.class);
                        startActivity(intent);
                    }
                    break;
                    case 3:{
                        Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                        startActivity(intent);
                    }
                    break;
                    default:
                }
            }
        });
    }
}
