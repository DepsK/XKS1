package com.dream.xukuan.stu8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.dream.xukuan.stu8.adapter.MyAdapter;
import com.dream.xukuan.stu8.bean.CityEntity;
import com.dream.xukuan.stu8.constant.Constant;
import com.dream.xukuan.stu8.util.MyJsonTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xk
 */
public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    private int page = 1;
    private static int cityId = 1;
    List<CityEntity> cityEntityList;
    private boolean isBottom;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startLoadTask();
    }

    private void startLoadTask() {
        String urlString = String.format(Constant.baseUrl,page,cityId);
        MyJsonTask task = new MyJsonTask(new MyJsonTask.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted(List<CityEntity> list) {
                cityEntityList.addAll(list);
            }
        });
        task.execute(urlString);
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.list_view);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        cityEntityList = new ArrayList<>();
        adapter = new MyAdapter(this,R.layout.item_layout,cityEntityList);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == SCROLL_STATE_IDLE&&isBottom){
                    page++;
                    startLoadTask();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount){
                    isBottom =true;
                }else {
                    isBottom =false;
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.beiJing:{
                        cityId = 1;
                        startLoadTask();
                    }
                    break;
                    case R.id.shangHai:{
                        cityId = 2;
                        startLoadTask();
                    }
                    break;
                    case R.id.shenZheng:{
                        cityId = 3;
                        startLoadTask();
                    }
                    break;
                    default:

                }
            }
        });
    }
}
