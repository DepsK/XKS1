package com.dream.xukuan.stu3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xukuan
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goTo1(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void goTo2(View view) {
        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void goTo3(View view) {
        Intent intent = new Intent(MainActivity.this, FourthActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void goTo4(View view) {
        Intent intent = new Intent(MainActivity.this, FifthActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
}
