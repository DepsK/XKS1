package com.dream.xukuan.stu11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author XK
 * @date 2018/3/2.
 */
public class MySqlHelper extends SQLiteOpenHelper {


    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlHelper(Context context) {
        this(context,"my_text_db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(_id integer primary key autoincrement, name text,score integer) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>newVersion){
            return;
        }else {
            db.execSQL("drop table if exits student");
            onCreate(db);
        }
    }
}