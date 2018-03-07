package com.dream.xukuan.stu14;

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
    public void click(View view){
        switch (view.getId()){
            case R.id.first:{
                Intent intent = new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.second:{
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.third:{
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.fourth:{
                Intent intent = new Intent(MainActivity.this,FourthActivity.class);
                startActivity(intent);
            }
            break;
            default:
        }
    }
}
