package com.dream.xukuan.stu2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goTo1(View view){
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    public void goTo2(View view){
        Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
}
