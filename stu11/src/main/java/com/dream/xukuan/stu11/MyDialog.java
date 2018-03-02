package com.dream.xukuan.stu11;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author XK
 * @date 2018/3/2.
 */
public class MyDialog extends Dialog {

    EditText nameEdit;
    EditText scoreEdit;

    private final OnEditOkListener listener;

    public interface OnEditOkListener{
        void onEditOK(String name, String score);
        void onEditError(String errorMsg);
    }

    public MyDialog(Context context,OnEditOkListener listener) {
        super(context,R.style.my_dialog_style);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dialog);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        scoreEdit = (EditText) findViewById(R.id.score_edit);
    }
    public void insert(View view){
        String name = nameEdit.getText().toString();
        String score = scoreEdit.getText().toString();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(score)){
            if(listener !=null){
                listener.onEditOK(name,score);

            }else{
                listener.onEditError("输入姓名或分数不能为空!");
            }
        }
    }
}