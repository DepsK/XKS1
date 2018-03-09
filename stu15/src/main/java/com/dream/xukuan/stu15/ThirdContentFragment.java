package com.dream.xukuan.stu15;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author XK
 * @date 2018/3/8.
 */
public class ThirdContentFragment extends Fragment {

    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third_contentfragment,null);
        textView = (TextView) view.findViewById(R.id.third_content_text);
        int index = getArguments().getInt("index");
        //读取assets资源中对应的文件
        AssetManager manager = getContext().getAssets();
        try {
            String[] movieFileNames  = manager.list("movies");
            String recentFile  = movieFileNames[index];
            InputStream inputStream = manager.open("movies/" + recentFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            while (true){
                String line = br.readLine();
                if(line ==null){
                    break;
                }
                sb.append(line+"\n");
            }
            textView.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

}