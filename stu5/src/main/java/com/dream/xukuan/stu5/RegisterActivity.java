package com.dream.xukuan.stu5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {



    private MyApp myApp;
    private HashMap<String, String> userMap;
    private EditText registerUser;
    private EditText registerPassWord;
    private EditText registerIsPassWord;
    private ImageView register_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        initView();
        myApp = (MyApp) getApplication();
        userMap = myApp.getUserMap();
    }

    private void initView() {
        registerUser = (EditText) findViewById(R.id.register_user);
        registerPassWord = (EditText) findViewById(R.id.register_password);
        registerIsPassWord = (EditText) findViewById(R.id.register_isPassword);
        register_fanhui = (ImageView) findViewById(R.id.register_fanhui);
        register_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Constant.REGISTER_NOTHING);
                finish();
            }
        });
    }

    public void register(View view) {
        String telNum = registerUser.getText().toString();
        String passWord = registerPassWord.getText().toString();
        String isPassWord = registerIsPassWord.getText().toString();
        // 判断：如果用户输入不为空，两次输入的密码匹配，且在已存在列表中未查到用户名，则可以注册
        if (TextUtils.isEmpty(telNum) || TextUtils.isEmpty(passWord)) {
            Toast.makeText(this, "密码和手机号输入不能为空！", Toast.LENGTH_LONG).show();
        } else {
            if (!telNum.matches("1[358]\\d{9}")) {

                Toast.makeText(this, "你输入的不是手机号！", Toast.LENGTH_LONG).show();
            } else {
                if (!passWord.equals(isPassWord)) {
                    Toast.makeText(this, "两次输入的密码不一致！", Toast.LENGTH_LONG)
                            .show();
                }else {
                    if(!userMap.containsKey(telNum)){
                        //存入用户列表
                        userMap.put(telNum,passWord);
                        Intent data = new Intent();
                        data.putExtra("user",telNum);
                        data.putExtra("password",passWord);
                        setResult(Constant.REGISTER_RETURN,data);
                        RegisterActivity.this.finish();
                    }else {
                        Toast.makeText(this, "该号码已经注册，请重新输入",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
