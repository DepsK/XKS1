package com.dream.xukuan.stu15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author XK
 * @date 2018/3/9.
 */
public class FourthFragment extends ListFragment {



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        String[] strs = {"数据1","数据1","数据1","数据1","数据1","数据1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,strs);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        //弹出对话框----DialogFragment实现对话框
        FourthDialogFragment dialogFragment = new FourthDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getFragmentManager(),"");
    }
}