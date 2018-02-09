package com.dream.xukuan.stu1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.last :{
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                SecondActivity.this.finish();
            }
            break;
            case R.id.next :{
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
                SecondActivity.this.finish();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}
