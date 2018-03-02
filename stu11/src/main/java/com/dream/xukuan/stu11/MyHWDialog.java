package com.dream.xukuan.stu11;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * @author XK
 * @date 2018/3/2.
 */
public class MyHWDialog extends Dialog {

    EditText nameEdit;
    EditText ageEdit;
    RadioGroup radioGroup;
    String sex;
    private final OnEditOkListener listener;

    public interface OnEditOkListener {
        void onEditOK(String name, String age,String sex);

        void onEditError(String errorMsg);
    }

    public MyHWDialog(Context context, OnEditOkListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dialog);
        nameEdit = (EditText) findViewById(R.id.hw_name_edit);
        ageEdit = (EditText) findViewById(R.id.hw_age_edit);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.man:{
                        sex = "男";
                    }
                    break;
                    case R.id.woman:{
                        sex = "女";
                    }
                    break;
                    default:
                }
            }
        });
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.cancel: {
                dismiss();
            }
            break;
            case R.id.enter: {
                String name = nameEdit.getText().toString();
                String age = ageEdit.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
                    if (listener != null) {
                        listener.onEditOK(name, age,sex);
                        dismiss();
                    } else {
                        listener.onEditError("输入姓名或年龄不能为空!");
                    }
                }
            }
            break;
            default:
        }
    }
}