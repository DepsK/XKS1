package com.dream.xukuan.stu10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void exit(View view){
        Intent intent = getIntent();
        intent.putExtra("isRePa",false);
        setResult(2,intent);
        UserActivity.this.finish();
    }
}
