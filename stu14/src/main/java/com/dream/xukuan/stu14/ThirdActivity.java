package com.dream.xukuan.stu14;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {

    ImageView lowBaImg;
    ImageView baImg;
    ImageView nextImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        lowBaImg = (ImageView) findViewById(R.id.third__low_battery);
        baImg = (ImageView) findViewById(R.id.third__battery);
        nextImg = (ImageView) findViewById(R.id.third_next);
        SetAnimation();
    }

    private void SetAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(lowBaImg,"scaleX",1.0f,2.5f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(lowBaImg,"scaleY",1.0f,2.5f);
        animator1.setDuration(2000);
        animator1.setRepeatCount(ObjectAnimator.INFINITE);
        animator1.setRepeatMode(ObjectAnimator.REVERSE);
        animator2.setDuration(2000);
        animator2.setRepeatCount(ObjectAnimator.INFINITE);
        animator2.setRepeatMode(ObjectAnimator.REVERSE);
        animator1.start();
        animator2.start();
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(baImg,"rotation",0,180);
        animator3.setDuration(2000);
        animator3.setRepeatCount(ObjectAnimator.INFINITE);
        animator3.setRepeatMode(ObjectAnimator.REVERSE);
        animator3.start();
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(nextImg,"translationY",1.0f,50.0f);
        animator4.setDuration(2000);
        animator4.setRepeatCount(ObjectAnimator.INFINITE);
        animator4.setRepeatMode(ObjectAnimator.REVERSE);
        animator4.setInterpolator(new LinearInterpolator());
        animator4.start();
    }
}
