package com.dream.xukuan.stu12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author XK
 * @date 2018/3/5.
 */
public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "my_pro.db", null, 1);
    }

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table product(_id integer primary  key autoincrement name text ,price integer )");
        db.execSQL("create table worker(_id integer primary  key autoincrement name text ,salary integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if   exists product");
            db.execSQL("drop table if   exists worker");
            db.execSQL("create table product(_id integer primary  key autoincrement name text ,price integer )");
            db.execSQL("create table worker(_id integer primary  key autoincrement name text ,salary integer )");
        }
    }

    public long insert(ContentValues values,String table){
        SQLiteDatabase db = getReadableDatabase();
        long rawId = db.insert(table, null, values);
        db.close();
        return rawId;
    }

    public int delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase db = getReadableDatabase();
        int num = db.delete(table, whereClause, whereArgs);
        db.close();
        return num;
    }

    public int update(String table, ContentValues values,String whereClause, String[] whereArgs){
        SQLiteDatabase db = getReadableDatabase();
        int num = db.update(table, values, whereClause, whereArgs);
        db.close();
        return num;
    }

    public Cursor query(String table, String[] columns,
                        String selection, String[] selectionArgs){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null, null);
        db.close();
        return cursor;
    }
}