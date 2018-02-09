package com.dream.xukuan.stu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout ll;
    private CheckBox check;
    CheckBox cash;
    CheckBox weChat;
    CheckBox aliPay;
    CheckBox bank;
    ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ll = (LinearLayout) findViewById(R.id.explain);
        check = (CheckBox) findViewById(R.id.check);
        cash = (CheckBox) findViewById(R.id.cash);
        weChat = (CheckBox) findViewById(R.id.weChat);
        aliPay = (CheckBox) findViewById(R.id.aliPay);
        bank = (CheckBox) findViewById(R.id.bank);
        fanhui = (ImageView) findViewById(R.id.fanHui);
        setListener();
    }

    private void setListener() {
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        cash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    weChat.setChecked(false);
                    aliPay.setChecked(false);
                    bank.setChecked(false);
                }
            }
        });
        weChat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cash.setChecked(false);
                    aliPay.setChecked(false);
                    bank.setChecked(false);
                }
            }
        });
        aliPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    weChat.setChecked(false);
                    cash.setChecked(false);
                    bank.setChecked(false);
                }
            }
        });

        bank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    weChat.setChecked(false);
                    aliPay.setChecked(false);
                    cash.setChecked(false);
                }
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.GONE);
                }
            }
        });
    }

}
