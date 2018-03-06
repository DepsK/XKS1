package com.dream.xukuan.stu12hw;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private static final String TAG = "ContactActivity";
    ListView listView;
    private static final Uri  RAW_CONTACT_URI = ContactsContract.RawContacts.CONTENT_URI;
    private static final String RAW_ID = ContactsContract.RawContacts._ID;
    private static final String DISPLAY_NAME = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY;
    //content://com.android.contacts/contacts
    private static final Uri DATA_URI = ContactsContract.Data.CONTENT_URI;
    private static final String RAW_CONTACT_ID = ContactsContract.Data.RAW_CONTACT_ID;
    private static final String DATA1 = ContactsContract.Data.DATA1;
    private static final String DATA2 = ContactsContract.Data.DATA2;
    private static final String MIMETYPE = ContactsContract.Data.MIMETYPE;
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        listView = (ListView) findViewById(R.id.contact_list);
    }
    //点击插入联系人信息
    public void add(View view){
        MyDialog dialog = new MyDialog(this, new MyDialog.OnEditOkListener() {
            @Override
            public void onEditOk(String name, String telNum, String homeAddress) {
                //第一步：需要获得data表raw_contact_id字段的值
                ContentValues values = new ContentValues();
                Uri rawUri = resolver.insert(RAW_CONTACT_URI, values);
                long id = ContentUris.parseId(rawUri);
                //第二步：插入数据到data
                ContentValues nameValues = new ContentValues();
                values.put(RAW_CONTACT_ID,id);
                values.put(DATA1,name);
                values.put(MIMETYPE,"vnd.android.cursor.item/name");
                resolver.insert(DATA_URI,nameValues);
                //telNum
                ContentValues telNumDataValues = new ContentValues();
                telNumDataValues.put(RAW_CONTACT_ID,id);
                telNumDataValues.put(DATA1,telNum);
                telNumDataValues.put(MIMETYPE,"vnd.android.cursor.item/phone_v2");
                telNumDataValues.put(DATA2,2);
                resolver.insert(DATA_URI,telNumDataValues);
                //address
                ContentValues homeAddressDataValues = new ContentValues();
                homeAddressDataValues.put(RAW_CONTACT_ID,id);
                homeAddressDataValues.put(DATA1,homeAddress);
                homeAddressDataValues.put(MIMETYPE,"vnd.android.cursor.item/postal-address_v2");
                homeAddressDataValues.put(DATA2,1);
                resolver.insert(DATA_URI,homeAddressDataValues);
            }

            @Override
            public void onEditError(String msg) {
                Toast.makeText(ContactActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
    //点击查询联系人信息
    public void check(View view){
        List<Person> persons = new ArrayList<>();
        //1.查询raw_contact表，
        Cursor rawCursor = resolver.query(RAW_CONTACT_URI, new String[]{RAW_ID, DISPLAY_NAME}, null, null, null);
        //2.遍历所有联系人信息,
        if(rawCursor.moveToFirst()){
            do {
                int raw_id = rawCursor.getInt(rawCursor.getColumnIndex(RAW_ID));
                String display_name = rawCursor.getString(rawCursor.getColumnIndex(DISPLAY_NAME));
                Person person = new Person(display_name,new ArrayList<String>());
                List<String> nums = person.getNums();
                // 3. 根据raw_contact表的_id字段来查询data表
                Cursor dataCursor = resolver.query(DATA_URI, null, RAW_CONTACT_ID + "=?", new String[]{raw_id + ""}, null);
                do {
                    //先获取类型
                    String mimeType = dataCursor.getString(dataCursor.getColumnIndex(MIMETYPE));
                    if(mimeType.equals("vnd.android.cursor.item/phone_v2")){
                        if(2==dataCursor.getInt(dataCursor.getColumnIndex(DATA2))){
                            String telNum = dataCursor.getString(dataCursor.getColumnIndex(DATA1));
                            Log.d(TAG, "click:手机：" + telNum + "---->" + raw_id);
                            nums.add("手机："+telNum);
                        }else if(1 == dataCursor.getInt(dataCursor.getColumnIndex(DATA2))){
                            String telNum = dataCursor.getString(dataCursor.getColumnIndex(DATA1));
                            Log.d(TAG, "click:住宅号码：" + telNum + "---->" + raw_id);
                            nums.add("住宅："+telNum);
                        }
                    }else if (mimeType.equals("vnd.android.cursor.item/name")) {
                        String name = dataCursor.getString(dataCursor.getColumnIndex(DATA1));
                        Log.d(TAG, "click:姓名：" + name + "---->" + raw_id);
                    }
                }while (dataCursor.moveToNext());
                persons.add(person);
            }while (rawCursor.moveToNext());
        }

    }
}
