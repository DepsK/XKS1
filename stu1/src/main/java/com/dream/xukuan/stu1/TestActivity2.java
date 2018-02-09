package com.dream.xukuan.stu1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity2 extends AppCompatActivity {

    public static String TAG = "xukuanqwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.d(TAG,"test2.onCreate");
    }

    public void goTo(View view){
        Intent intent = new Intent(TestActivity2.this,TestActivity1.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"test2.onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"test2.onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"test2.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"test2.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"test2.onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"test2.onDestroy");
    }
}
