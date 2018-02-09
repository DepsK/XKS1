package com.dream.xukuan.stu3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ThirdActivity extends AppCompatActivity {

    AutoCompleteTextView auto;
    String[] data = {"13125486531","15625448651","13425148651","13162238651","13182678651","13178686651","13125499876"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        auto = (AutoCompleteTextView) findViewById(R.id.auto);
        auto.setThreshold(1);
        auto.setCompletionHint("请输入手机号码");
        auto.setDropDownHeight(500);
        auto.setDropDownBackgroundResource(R.mipmap.ic_launcher);
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,data);
        auto.setAdapter(adapter);
    }
}
