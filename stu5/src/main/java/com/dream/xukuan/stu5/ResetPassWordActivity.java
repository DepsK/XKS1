package com.dream.xukuan.stu5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class ResetPassWordActivity extends AppCompatActivity {

    private MyApp myApp;
    private HashMap<String, String> userMap;
    private ImageView fpFanHui;
    private EditText telNumEdit;
    private EditText resetPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();
        myApp = (MyApp) getApplication();
        userMap = myApp.getUserMap();
    }

    private void initView() {
        fpFanHui = (ImageView) findViewById(R.id.findPassword_fanhui);
        telNumEdit = (EditText) findViewById(R.id.telNum);
        resetPassWord = (EditText) findViewById(R.id.resetPassWord);
        String userName = getIntent().getStringExtra("userName");
        if(!TextUtils.isEmpty(userName)){
            telNumEdit.setText(userName);
        }
        fpFanHui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void goToReset(View view){
        String telNum = telNumEdit.getText().toString();
        String resetPW = resetPassWord.getText().toString();
        if (TextUtils.isEmpty(telNum) || TextUtils.isEmpty(resetPW)) {
            Toast.makeText(this, "密码和手机号输入不能为空！", Toast.LENGTH_LONG).show();
        }else {
            if(userMap.containsKey(telNum)){
                userMap.put(telNum,resetPW);
                Intent intent = new Intent();
                intent.putExtra("telNum",telNum);
                intent.putExtra("resetPW",resetPW);
                setResult(Constant.RESET_RETURN,intent);
            }
        }
    }
}
