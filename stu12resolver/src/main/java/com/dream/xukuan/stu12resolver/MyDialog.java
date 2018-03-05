package com.dream.xukuan.stu12resolver;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyDialog extends Dialog implements View.OnClickListener {

    private final OnEditOKListener listener;
    EditText nameEdit;
    EditText priceEdit;
    Button button;

    public interface OnEditOKListener{
        void onEditOk(String name,String price);
        void onEditError(String msg);
    }


    public MyDialog(Context context,OnEditOKListener listener) {
        super(context,R.style.my_dialog_style);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        priceEdit = (EditText) findViewById(R.id.price_edit);
        button = (Button) findViewById(R.id.click);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = nameEdit.getText().toString();
        String price = priceEdit.getText().toString();
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(price)){
            if(listener !=null){
                listener.onEditOk(name,price);
                dismiss();
            }
        }else {
            listener.onEditError("输入内容不能为空");
        }
    }
}
