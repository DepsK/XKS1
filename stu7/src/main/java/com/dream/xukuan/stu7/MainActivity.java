package com.dream.xukuan.stu7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.xukuan.stu7.adapter.MyAdapter;
import com.dream.xukuan.stu7.bean.MyNewsEntity;
import com.dream.xukuan.stu7.constant.Constant;
import com.dream.xukuan.stu7.util.HttpUtil;
import com.dream.xukuan.stu7.util.MyJsonTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    HttpUtil task;
    ListView listView;
    TextView emptyView;
    List<MyNewsEntity> entityList;
    int page = 1;
    boolean isBottom = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setList();


    }

    private void setList() {
        listView.setEmptyView(emptyView);
        entityList = new ArrayList<>();
        adapter = new MyAdapter(this,R.layout.item_layout,entityList);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE&&isBottom){
                    page++;
                    startLoadJson(page);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount){
                    isBottom = true;
                }else {
                    isBottom = false;
                }
            }
        });
        View footView = LayoutInflater.from(this).inflate(R.layout.footer_layout,null);
        listView.addFooterView(footView);
        //下载数据
        startLoadJson(page);
    }

    private void startLoadJson(final int page) {
        MyJsonTask task = new MyJsonTask(new MyJsonTask.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted(List<MyNewsEntity> list) {
                MainActivity.this.entityList = list;
                if(entityList == null){
                    Toast.makeText(MainActivity.this, "网络超时，再次上拉。。。", Toast.LENGTH_LONG).show();
                    MainActivity.this.page --;
                    startLoadJson(page);
                }else {
                    adapter.addAll(list);
                }
            }
        });
        String urlString = String.format(Constant.baseUrl, 10,10,page);
        task.execute(urlString);

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_view);
        emptyView = (TextView) findViewById(R.id.empty_tv);

    }
}
