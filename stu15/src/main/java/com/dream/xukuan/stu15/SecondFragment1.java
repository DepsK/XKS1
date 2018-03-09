package com.dream.xukuan.stu15;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author XK
 * @date 2018/3/9.
 */
public class SecondFragment1 extends Fragment implements View.OnClickListener {

    EditText editText;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment1,null);
        editText = (EditText) view.findViewById(R.id.second_frag_edit);
        button = (Button) view.findViewById(R.id.second_frag_btn);
        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        String msg = editText.getText().toString();
        if(TextUtils.isEmpty(msg)){
            Toast.makeText(getActivity(),"输入不能为空!",Toast.LENGTH_SHORT).show();
        }else {
            FragmentManager fg = getFragmentManager();
            SecondFragment2 fragment = (SecondFragment2) fg.findFragmentById(R.id.second_frag2);
            fragment.setTVText(msg);
        }
    }
}