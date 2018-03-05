package com.dream.xukuan.stu12resolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ContentResolver resolver;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        resolver = getContentResolver();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,1,"插入数据到产品表");
        menu.add(0,2,2,"插入数据到员工表");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:{
                insertToProductData();
            }
            break;
            case 2:{
                insertToWorkerData();
            }
            break;
            default:
        }
        return true;
    }

    private void insertToWorkerData() {
        MyDialog dialog = new MyDialog(MainActivity.this, new MyDialog.OnEditOKListener() {
            @Override
            public void onEditOk(String name, String salary) {
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("salary",salary);
                Uri uri = Uri.parse("content://myprovider/worker");
                resolver.insert(uri,values);
            }

            @Override
            public void onEditError(String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void insertToProductData() {
        MyDialog dialog = new MyDialog(MainActivity.this, new MyDialog.OnEditOKListener() {
            @Override
            public void onEditOk(String name, String price) {
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("price",price);
                Uri uri = Uri.parse("content://myprovider/product");
                resolver.insert(uri,values);
            }

            @Override
            public void onEditError(String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
