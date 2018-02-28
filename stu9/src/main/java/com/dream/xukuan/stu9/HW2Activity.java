package com.dream.xukuan.stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HW2Activity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw2);
        imageView = (ImageView) findViewById(R.id.hw2_image);
    }

    public void load(View view){
        MyImageLoader loader = new MyImageLoader(this,imageView);
        String imgUrl = "https://img4.duitang.com/uploads/item/201603/26/20160326193535_dj8cx.jpeg";
        loader.execute(imgUrl);
    }
}
