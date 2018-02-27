package com.dream.xukuan.stu9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        editText = (EditText) findViewById(R.id.edit_text);
        registerForContextMenu(editText);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //设置菜单的标题部分
        //menu.setHeaderTitle("请选择职业");
        View headView = LayoutInflater.from(this).inflate(R.layout.head_layout,null);
        menu.setHeaderView(headView);
        //添加菜单项的第二种方法
        menu.add(0,//组id
                1,//选项的id
                1,//选项在菜单中的顺序
                "清洁工");//选项的标题内容
        menu.add(0,2,2,"洗碗工");
        menu.add(0,3,3,"搬砖");
        SubMenu subMenu = menu.addSubMenu(0,4,4,"互联网行业");
        subMenu.add("架构师");
        subMenu.add("html工程师");
        subMenu.add("android工程师");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        editText.setText(item.getTitle());
        return true;
    }
}
