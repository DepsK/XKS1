package com.dream.xukuan.stu11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class HWActivity extends AppCompatActivity {

    private static final String TAG = "HWActivity";
    ListView listView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        listView = (ListView) findViewById(R.id.list_view);
        setListView();
    }

    private void setListView() {
        Cursor cursor = MyHwDBHelper.getInstance(HWActivity.this).query("student",new String[]{"_id","name","age","sex"});
      adapter = new MyAdapter(HWActivity.this,cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                registerForContextMenu(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"添加");
        menu.add(0,2,2,"查询");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:{
                insertData();
            }
            break;
            case 2:{
                queryData();
            }
            break;
            default:
        }
        return true;
    }

    private void queryData() {

    }

    private void insertData() {
        MyHWDialog dialog = new MyHWDialog(HWActivity.this, new MyHWDialog.OnEditOkListener() {
            @Override
            public void onEditOK(String name, String age ,String sex) {
                //插入记录到数据库
                MyHwDBHelper helper = MyHwDBHelper.getInstance(HWActivity.this);
                ContentValues  contentValue = new ContentValues();
                contentValue.put("name",name);
                contentValue.put("age",age);
                contentValue.put("sex",sex);
                helper.insert("student",contentValue);

                //重新查询
                Cursor c = MyHwDBHelper.getInstance(HWActivity.this).query("student", null);
                //置换适配器中的数据
                adapter.swapCursor(c);
            }

            @Override
            public void onEditError(String errorMsg) {

            }
        });
        dialog.show();
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        menu.add(0,1,1,"操作：");
        menu.add(0,2,2,"删除");
        menu.add(0,3,3,"修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 2:{
                deleteData();
            }
            break;
            case 3:{
                updateData();
            }
            break;
            default:
        }
        return true;
    }

    private void updateData() {
        SQLiteDatabase db = MyHwDBHelper.getInstance(HWActivity.this).getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","xiaowei");
        values.put("age",20);
        values.put("sex","女");
        int num = db.update("student", values, "name like ?", new String[]{"%xu%"});
        Log.d(TAG,"updateDate 修改的记录数量:"+num);
    }

    private void deleteData() {
        SQLiteDatabase db = MyHwDBHelper.getInstance(HWActivity.this).getReadableDatabase();
        int num = db.delete("student", "id=?", new String[]{"1"});
        Log.d(TAG,"deleteData: 删除记录数："+num);
    }
}
