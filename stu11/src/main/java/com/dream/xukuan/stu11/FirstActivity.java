package com.dream.xukuan.stu11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";
    MySqlHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        helper = new MySqlHelper(FirstActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"添加数据");
        menu.add(0,2,2,"删除数据");
        menu.add(0,3,3,"查询数据");
        menu.add(0,4,4,"修改数据");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:{
                //增加数据到数据库的表中
                insertData();
            }
            break;
            case 2:{
                //删除数据
                deleteData();
            }
            break;
            case 3:{
                //查询数据
                queryData();
            }
            break;
            case 4:{
                //修改数据
                updateData();
            }
            break;
            default:
        }
        return true;
    }

    private void updateData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name","xiaowei");
        values.put("score",100);
        int num = db.update("student", values, "name like ?", new String[]{"%xu%"});
        Log.d(TAG,"updateDate 修改的记录数量:"+num);
    }

    private void queryData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("student", new String[]{"name", "score"},
                null,
                null,
                null,
                null,
                null);
        int position = cursor.getPosition();
        Log.d(TAG, "queryData: 当前位置"+position);
        Log.d(TAG, "queryData: 记录总数"+cursor.getCount());
        Log.d(TAG, "queryData: 字段的数量（列数）"+cursor.getColumnCount());
        cursor.moveToFirst();
        Log.d(TAG, "queryData: 当前位置"+position);
        if(cursor.moveToFirst()){
            while (true){
                int nameIndex = cursor.getColumnIndex("name");
                String name = cursor.getString(nameIndex);
                int scoreIndex = cursor.getColumnIndex("score");
                int score = cursor.getInt(scoreIndex);
                Log.d(TAG,"queryData:"+name+"-----"+score);
                if(!cursor.moveToNext()){
                    break;
                }
            }
        }
    }

    private void deleteData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        int num = db.delete("student", "id=?", new String[]{"1"});
        Log.d(TAG,"deleteData: 删除记录数："+num);
    }

    private void insertData() {
        MyDialog dialog = new MyDialog(FirstActivity.this, new MyDialog.OnEditOkListener() {
            @Override
            public void onEditOK(String name, String score) {
                //得到数据库对象：系统内部才会回调helper的onCreate方法，才会创建数据库文件
                //数据库文件保存在/data/data/包名/database目录下
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("score",score);
                db.insert("student",null,values);
                db.close();
            }

            @Override
            public void onEditError(String errorMsg) {
                Toast.makeText(FirstActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
