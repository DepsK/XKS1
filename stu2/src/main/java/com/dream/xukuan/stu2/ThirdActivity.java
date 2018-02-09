package com.dream.xukuan.stu2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {

    CheckBox check;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        check = (CheckBox) findViewById(R.id.agree);
        imageView = (ImageView) findViewById(R.id.enter);
        button = (Button) findViewById(R.id.button);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    imageView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }else {
                    imageView.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
