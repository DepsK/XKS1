package com.dream.xukuan.stu9;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class SixthActivity extends AppCompatActivity {

    PopupWindow window;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        imageView = (ImageView) findViewById(R.id.image);
        window = new PopupWindow();
        // 必须设置宽和高。否则不会显示
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View popupView = LayoutInflater.from(SixthActivity.this).inflate(R.layout.popup_layout,null);
        window.setContentView(popupView);
        //用户点击其他地方是否隐藏已经显示的PopupWindow。注意：只有同时设置setBackgroundDrawable才有效果。
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.RED));
        window.setFocusable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(window.isShowing()){
                    window.dismiss();
                }else {
                    // 在某个具体的坐标上展示PopupWindow自定义菜单
                    window.showAtLocation(findViewById(R.id.root), Gravity.BOTTOM,0,100);
                }

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:{
                if(window.isShowing()){
                    window.dismiss();
                }else {
                    // 在某个具体的坐标上展示PopupWindow自定义菜单
                    window.showAtLocation(findViewById(R.id.root), Gravity.BOTTOM,0,100);
                }
            }
            break;
            default:
        }
        return true;
    }
}
