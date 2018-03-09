package com.dream.xukuan.stu15;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author XK
 * @date 2018/3/8.
 */
public class SecondFragment3 extends Fragment {

    OnProgressChangeListener listener;
    SeekBar seekBar;
    TextView textView;

    private static final String TAG = "SecondFragment3";

    public interface OnProgressChangeListener{
        void onProgressChange(int progress);
    }

    public void setOnProgressChangeListener(OnProgressChangeListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment3,null);
        seekBar = (SeekBar) view.findViewById(R.id.second_frag3_seek);
        textView = (TextView) view.findViewById(R.id.second_frag3_text);
        setSeek();
        Bundle bundle = getArguments();
        textView.setText(bundle.getString("data","暂无数据"));
        return view;
    }

    public void setTVText(String msg){
        textView.setText(msg);
    }

    private void setSeek() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(listener != null){
                    listener.onProgressChange(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}