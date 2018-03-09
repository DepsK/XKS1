package com.dream.xukuan.stu15;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ThirdContentActivity extends AppCompatActivity {


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_content_layout);
        Bundle bundle = getIntent().getExtras();
        ThirdContentFragment fragment = new ThirdContentFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.third_content_layout,fragment);
        transaction.commit();
    }
}
