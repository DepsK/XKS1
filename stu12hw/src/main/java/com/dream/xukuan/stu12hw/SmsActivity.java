package com.dream.xukuan.stu12hw;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    ListView listView;
    private static final String _ID = Telephony.Sms._ID;
    private static final String BODY = Telephony.Sms.BODY;
    private static final String ADDRESS = Telephony.Sms.ADDRESS;
    private static final String TYPE = Telephony.Sms.TYPE;
    private Uri uri = Uri.parse("content://sms");
    private Cursor cursor;
    private String[] columns = {_ID,BODY,ADDRESS,TYPE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        listView = (ListView) findViewById(R.id.sms_list);
        ContentResolver resolver = getContentResolver();
        resolver.query(uri,columns,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_media_item_layout,
                cursor,
                new String[]{BODY,ADDRESS,TYPE},
                new int[]{R.id.text1,R.id.text2,R.id.text3},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId()==R.id.text3){
                    TextView textView = (TextView) view;
                    String typeStr = null;
                    int type = cursor.getInt(columnIndex);
                    if(type==1){
                        typeStr = "收件箱";
                    }else if(type==2){
                        typeStr = "已发送";
                    }else if(type==3){
                        typeStr ="草稿";
                    }else if(type ==4){
                        typeStr ="发送失败";
                    }else {
                        typeStr = "未知";
                    }
                    textView.setText(typeStr);
                    return true;
                }
                return false;
            }
        });
        listView.setAdapter(adapter);

    }
}
