package com.dream.xukuan.stu1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity1 extends AppCompatActivity {

    private static final String TAG = "xukuanqwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        Log.d(TAG,"test1.onCreate");
    }

    public void goTo(View view){
        Intent intent = new Intent(TestActivity1.this,TestActivity2.class);
        startActivity(intent);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"test1.onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"test1.onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"test1.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"test1.onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"test1.onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"test1.onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG,"test1.onConfigurationChanged");
    }
}
