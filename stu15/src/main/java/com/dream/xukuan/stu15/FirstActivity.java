package com.dream.xukuan.stu15;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";
    FirstFragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.i(TAG, "onCreate: ....");
        fragment2 = new FirstFragment2();

    }

    /**
     * 动态添加Fragment:
     * 1.定义类继承Fragment,重写onCreateView加载视图，返回视图对象
     * 2.创建Fragment对象
     * 3.获得碎片管理器对象:v4包getSupportFragmentManager  /  app包 getFragmentManager()
     * 4.用管理器获得事务对象
     * 5.用事务来添加fragment----需要在activity的布局中增加一个布局控件(FrameLayout)来接收fragment
     * 6.提交事务
     * @param view
     */
    public void click(View view){
        FragmentManager fg = getSupportFragmentManager();
        FragmentTransaction transaction = fg.beginTransaction();
        transaction.add(R.id.first_fragment_container,fragment2);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ....");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ....");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause: ....");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ....");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: .....");
    }
}
