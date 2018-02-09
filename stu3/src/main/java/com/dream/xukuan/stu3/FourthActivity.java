package com.dream.xukuan.stu3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ScrollView;


public class FourthActivity extends AppCompatActivity {

    ScrollView scroll;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        scroll = (ScrollView) findViewById(R.id.scroll);
        imageView = (ImageView) findViewById(R.id.image);
        setView();
    }

    private void setView() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll.scrollTo(0,0);
            }
        });
    }
}
