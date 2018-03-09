package com.dream.xukuan.stu15;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    SecondFragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.tv);
    }



    public void clickSend1(View view){
        String msg = "传入到frag2的数据";
        FragmentManager manager = getSupportFragmentManager();
        SecondFragment2 fragment2 = (SecondFragment2) manager.findFragmentById(R.id.second_frag2);
        fragment2.setTVText(msg);
    }

    public void clickAdd(View view){
        fragment3 =new SecondFragment3();
        fragment3.setOnProgressChangeListener(new SecondFragment3.OnProgressChangeListener() {
            @Override
            public void onProgressChange(int progress) {
                textView.setText("得到frag3传回的数据"+progress);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("data","传递给frag3的数据");
        fragment3.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.second_fragment_container,fragment3);
        transaction.commit();
    }

    public void clickSend2(View view){
        String msg = "第二次传递";
        if(fragment3 !=null){
           /* Bundle bundle = new Bundle();
            bundle.putString("data",msg);
            fragment3.setArguments(bundle);*/
            fragment3.setTVText(msg);
        }

    }
}
