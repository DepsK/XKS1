package com.dream.xukuan.stu14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView starView;
    ImageView backView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        starView = (ImageView) findViewById(R.id.second_star);
        backView = (ImageView) findViewById(R.id.second_back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* AnimationSet set = new AnimationSet(true);
                Animation animation1 = new ScaleAnimation(1.0f,0,1.0f,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation1.setDuration(4000);
                set.addAnimation(animation1);
                float height = getResources().getDisplayMetrics().heightPixels;
                float width = getResources().getDisplayMetrics().widthPixels;
                Animation animation2 = new TranslateAnimation(0,-500,0,1000);
                animation2.setDuration(4000);
                set.addAnimation(animation2);*/

                Animation animation = AnimationUtils.loadAnimation(SecondActivity.this, R.anim.second_animation);
                starView.startAnimation(animation);
            }
        });
    }
}
