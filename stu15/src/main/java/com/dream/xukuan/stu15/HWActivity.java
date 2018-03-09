package com.dream.xukuan.stu15;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HWActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        radioGroup = (RadioGroup) findViewById(R.id.hw_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                int index = Integer.parseInt(button.getTag().toString());
                addFragment(index);
            }
        });
        fragments = new Fragment[3];
        addFragment(0);
    }

    int lastIndex = -1;
    private void addFragment(int tag) {
        if(lastIndex == tag){
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //如果之前存在其他的fragment,需要隐藏之前的fragment
        if(lastIndex>0){
            //transaction.hide(fragments[lastIndex]);//隐藏上次的fragment
           //transaction.remove(fragments[lastIndex]);//移除
            transaction.detach(fragments[lastIndex]);
        }
        if(fragments[tag]==null){
            switch (tag){
                case 0:{
                    fragments[tag] = new FourthConversationFragment();
                }
                break;
                case 1:{
                    fragments[tag] = new FourthContactFragment();
                }
                break;
                case 2:{
                    fragments[tag] = new FourthPluginFragment();
                }
                break;
                default:
            }
            transaction.add(R.id.hw_fragment_container,fragments[tag]);
        }else {
            transaction.attach(fragments[tag]);
        }
        transaction.commit();
        lastIndex =tag;
    }
}
