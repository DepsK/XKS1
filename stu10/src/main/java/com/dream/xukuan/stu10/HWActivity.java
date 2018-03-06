package com.dream.xukuan.stu10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HWActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        imageView = (ImageView) findViewById(R.id.hw_image);
    }

    public void load(View view){
        String imgUrl = "http://img.taopic.com/uploads/allimg/140227/235111-14022F9410899.jpg";
        MyLoader loader = new MyLoader(HWActivity.this);
        loader.load(imgUrl).into(imageView);
    }
}
