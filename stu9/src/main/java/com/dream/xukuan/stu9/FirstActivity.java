package com.dream.xukuan.stu9;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Method;

public class FirstActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = (TextView) findViewById(R.id.text);
    }

    public void setMenuIconEnable(Menu menu, boolean flag) {
        try {
            // 得到MenuBuilder类对应的字节码:如果不是v7包，
            //则类名是"com.android.internal.view.menu.MenuBuilder"
            Class<?> aClass = Class.forName("android.support.v7.view.menu.MenuBuilder");
            // 得到MenuBuilder类中的setOptionalIconsVisible(boolean flag)方法
            Method method = aClass.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            // 设置方法的访问性
            method.setAccessible(true);
            // 执行该方法
            // menu.setOptionalIconsVisible(true);
            method.invoke(menu, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        setMenuIconEnable(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.textColorItem: {
                // 随机生成字体颜色，设置给textview
                int red = (int) (Math.random() * 256);
                int green = (int) (Math.random() * 256);
                int blue = (int) (Math.random() * 256);
                int color = Color.rgb(red, green, blue);
                textView.setTextColor(color);
            }
            break;
            case R.id.bigger_item: {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() + 10);
            }
            break;
            case R.id.lowitem: {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() - 10);
            }
            break;
            default:
        }
        return true;
    }
}
