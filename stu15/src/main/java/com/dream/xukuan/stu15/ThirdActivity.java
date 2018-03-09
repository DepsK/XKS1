package com.dream.xukuan.stu15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        if(MyUtils.isLand(ThirdActivity.this)){
            //如果加载界面时时横屏，需要加入第一个内容Fragment
            ThirdContentFragment fragment = new ThirdContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",0);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.third_content_layout,fragment)
                    .commit();
        }
    }
}
