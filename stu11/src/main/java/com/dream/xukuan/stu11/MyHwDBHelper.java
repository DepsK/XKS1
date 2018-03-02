package com.dream.xukuan.stu11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author XK
 * @date 2018/3/2.
 */
public class MyHwDBHelper extends SQLiteOpenHelper {

    public MyHwDBHelper(Context context){
        this(context,"my_hw.db",null,1);
    }
    private static MyHwDBHelper helper = null;

    public static MyHwDBHelper getInstance(Context context) {
        if (helper == null){
            helper = new MyHwDBHelper(context);
        }
        return helper;
    }

    public MyHwDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(_id integer primary key autoincrement,name text , age integer,sex,text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion>newVersion){
            return;
        }else {
            db.execSQL("drop table if exists student ");
            onCreate(db);
        }
    }

    public void insert(String table , ContentValues values){
        SQLiteDatabase db = getReadableDatabase();
        db.insert(table,null,values);
    }

    public Cursor query(String table,String[] columns){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        return cursor;
    }
}