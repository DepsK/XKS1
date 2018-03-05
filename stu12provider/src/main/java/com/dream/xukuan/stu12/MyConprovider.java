package com.dream.xukuan.stu12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * @author XK
 * @date 2018/3/5.
 */
public class MyConprovider extends ContentProvider {

    private MyHelper helper;

    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int PRODUCT_CODE = 100;
    public static final int WORKER_CODE = 101;


     static {
         uriMatcher.addURI("myprover","product",PRODUCT_CODE);
         uriMatcher.addURI("myprover","worker",WORKER_CODE);
     }


    @Override
    public boolean onCreate() {
        //创建数据库
        helper = new MyHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case PRODUCT_CODE:{
                cursor = helper.query("product", projection, selection, selectionArgs);
            }
            break;
            case WORKER_CODE:{
                cursor = helper.query("worker",projection,selection,selectionArgs);
            }
            break;
            default:
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case PRODUCT_CODE:{
                helper.insert(values,"product");
            }
            break;
            case WORKER_CODE:{
                helper.insert(values,"worker");
            }
            break;
            default:
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int num = 0;
       switch (uriMatcher.match(uri)){
           case PRODUCT_CODE :{
               num = helper.delete("product",selection,selectionArgs);
           }
           break;
           case WORKER_CODE:{
               num = helper.delete("worker",selection,selectionArgs);
           }
           break;
           default:
       }
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int num = 0;
        switch (uriMatcher.match(uri)){
            case PRODUCT_CODE :{
                num = helper.update("product",values,selection,selectionArgs);
            }
            break;
            case WORKER_CODE:{
                num = helper.update("worker",values,selection,selectionArgs);
            }
            break;
            default:
        }
        return num;
    }
}