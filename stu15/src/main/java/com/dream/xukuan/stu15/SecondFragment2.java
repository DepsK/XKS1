package com.dream.xukuan.stu15;

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
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author XK
 * @date 2018/3/9.
 */
public class SecondFragment2 extends Fragment {

    TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment2,null);
        textView = (TextView) view.findViewById(R.id.second_frag2_text);
        return view;
    }

    public void setTVText(String msg){
        textView.setText(msg);
    }

}