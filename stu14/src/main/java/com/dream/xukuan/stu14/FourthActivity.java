package com.dream.xukuan.stu14;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class FourthActivity extends AppCompatActivity {

    ImageView imageView;
    private int screenWidth;
    private int pigWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        imageView = (ImageView) findViewById(R.id.fourth_pig);
        setAnimation();
    }

    private void setAnimation() {

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        pigWidth = imageView.getWidth();
        //得到一个视图体系创建的观察者
        ViewTreeObserver observer = imageView.getViewTreeObserver();
        //给观察者设置绘图完成的监听器
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(pigWidth == 0){
                    pigWidth = imageView.getWidth();
                    boolean leftTORight = true;
                    startTransition(leftTORight);
                }
            }
        });
        imageView.setBackgroundResource(R.drawable.fourth_animation);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        drawable.start();
    }

    private void startTransition(final boolean leftTORight) {
        //如果从左到右跑：起点是0  终点是screenWidth-pigWidth
        float startX = leftTORight ? 0: (screenWidth-pigWidth);
        float endX = leftTORight ? (screenWidth-pigWidth) :0;
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationX",
                startX,endX);
        animator.setDuration(4000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startRomate(leftTORight);
            }
        });
        animator.start();

    }

    private void startRomate(final boolean leftTORight) {
        float startRote = leftTORight?0:180;
        float endRote = leftTORight?180:0;
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotationY",startRote,endRote);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startTransition(!leftTORight);
            }
        });
        animator.start();
    }
}
