package com.dream.xukuan.stu12hw;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyDialog extends Dialog {

    private final OnEditOkListener listener;
    EditText nameEdit;
    EditText telNumEdit;
    EditText addressEdit;
    Button button;

    public interface OnEditOkListener{
        void onEditOk(String name,String telNum,String homeAddress);
        void onEditError(String msg);
    }

    public MyDialog(Context context,OnEditOkListener listener) {
        super(context,R.style.my_style);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        telNumEdit = (EditText) findViewById(R.id.num_edit);
        addressEdit = (EditText) findViewById(R.id.address_edit);
        button = (Button) findViewById(R.id.insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //输入完成了：获得输入的内容
                String name = nameEdit.getText().toString();
                String telNum = telNumEdit.getText().toString();
                String homeAddress = addressEdit.getText().toString();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(telNum)&&!TextUtils.isEmpty(homeAddress)){
                    if(listener !=null){
                        listener.onEditOk(name,telNum,homeAddress);
                        dismiss();
                    }
                }else {
                    listener.onEditError("输入不能为空！");
                }
            }
        });
    }
}
