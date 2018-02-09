package com.dream.xukuan.stu5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {


    private HashMap<String, String> userMap;
    private MyApp myApp;
    private EditText editUser;
    private EditText editPassword;
    private TextView forgetText;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        myApp = (MyApp) getApplication();
        userMap = myApp.getUserMap();
        setTextView();
    }

    private void setTextView() {
        forgetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPassWordActivity.class);
                String userName = editUser.getText().toString();
                if(!TextUtils.isEmpty(userName)){
                    intent.putExtra("userName",userName);
                }
                startActivityForResult(intent,Constant.RESET_CODE);
            }
        });

        // 跳到注册页面：如果点击提交按钮，返回数据，取消注册
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, Constant.REGISTER_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REGISTER_CODE) {
            if (resultCode == Constant.REGISTER_RETURN) {
                String user = getIntent().getStringExtra("user");
                String passWord = getIntent().getStringExtra("password");

            }
        }
        if (requestCode == Constant.RESET_CODE){
            if (resultCode == Constant.RESET_RETURN) {
                String user = getIntent().getStringExtra("telNum");
                String passWord = getIntent().getStringExtra("resetPW");
            }
        }

    }

    private void initView() {
        editUser = (EditText) findViewById(R.id.edit_user);
        editPassword = (EditText) findViewById(R.id.edit_password);
        forgetText = (TextView) findViewById(R.id.forgetPassword);
        registerText = (TextView) findViewById(R.id.register);
    }

    public void login(View view) {
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码和手机号输入不能为空！", Toast.LENGTH_SHORT);
        } else {
            //查询用户是否注册，检查密码是否匹配
            if (userMap.containsKey(user) && userMap.get(user).equals(password)) {
                Intent intent = new Intent(this, FifthActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "用户不存在，或者密码不匹配！", Toast.LENGTH_LONG).show();
            }
        }
    }


}
