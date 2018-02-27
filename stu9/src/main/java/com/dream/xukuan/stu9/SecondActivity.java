package com.dream.xukuan.stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加菜单项的第二种方法
        menu.add(0,1,1,"清洁工");
        SubMenu subMenu = menu.addSubMenu(0, 4, 4, "互联网行业");
        subMenu.add("架构师");
        subMenu.add("html工程师");
        subMenu.add("android工程师");

        return true;
    }
}
