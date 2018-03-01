package com.dream.xukuan.stu10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText passwordEdit;
    RadioButton reButton;
    boolean isRe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initViews();
        SharedPreferences sp = getSharedPreferences("my_user", MODE_PRIVATE);
        if (sp.getBoolean("isRe", false)) {
            Intent intent = new Intent(FirstActivity.this, UserActivity.class);
            startActivity(intent);
        } else {
            String name = sp.getString("u_name", "");
            String password = sp.getString("u_password", "");
            nameEdit.setText(name);
            passwordEdit.setText(password);
        }
    }

    private void initViews() {
        nameEdit = (EditText) findViewById(R.id.edit_name);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        reButton = (RadioButton) findViewById(R.id.re_password);
        reButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isRe = true;
                } else {
                    isRe = false;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            boolean isRePa = data.getBooleanExtra("isRePa", false);
            SharedPreferences sp = getSharedPreferences("my_user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isRe",isRePa);
            editor.commit();

        }
    }

    public void login(View view) {
        String name = nameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(password)) {
            Toast.makeText(FirstActivity.this, "输入帐号或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sp = getSharedPreferences("my_user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("u_name", name);
            editor.putString("u_password", password);
            editor.putBoolean("isRe", isRe);
            editor.commit();
            Intent intent = new Intent(FirstActivity.this, UserActivity.class);
            startActivityForResult(intent, 1);

        }
    }
}
