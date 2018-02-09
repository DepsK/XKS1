package com.dream.xukuan.stu6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CityListActivity extends AppCompatActivity {

    ListView cityList;
    String[] names = {"深圳", "北京", "上海", "广州", "成都", "南昌"};
    String[] numbers = {"101280601", "101010100", "101020100", "101280101", "101270101", "101240101"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initView();
        setListView();
    }

    private void setListView() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        cityList.setAdapter(adapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityId = numbers[position];
                String cityName = names[position];
                Intent intent = new Intent();
                intent.putExtra("cityId", cityId);
                intent.putExtra("cityName", cityName);
                setResult(Constant.RESULT_CODE, intent);
                CityListActivity.this.finish();
            }
        });
    }

    private void initView() {
        cityList = (ListView) findViewById(R.id.city_list);
    }
}
