package com.dream.xukuan.stu15;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author XK
 * @date 2018/3/8.
 */
public class ThirdTitleFragment extends Fragment {


    String[] titles = {"绣春刀", "变形金刚4", "后会无期", "3D肉蒲团"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third_titlefragment, null);
        ListView listView = (ListView) view.findViewById(R.id.third_title_list);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                //选中title,
                //如果当前是横屏，就添加内容Fragment到MainActivity
                if(MyUtils.isLand(getContext())){
                    ThirdContentFragment fragment = new ThirdContentFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.third_content_layout,fragment);
                    transaction.commit();
                }else {
                    Intent intent = new Intent(getActivity(),ThirdContentActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}