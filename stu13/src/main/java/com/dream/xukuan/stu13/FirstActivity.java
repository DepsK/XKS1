package com.dream.xukuan.stu13;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FirstActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView listView;
    SimpleCursorAdapter adapter;
    private static final Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final String _ID = ContactsContract.CommonDataKinds.Phone._ID;
    private static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    private static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    static String[] columns = {_ID, DISPLAY_NAME, NUMBER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new SimpleCursorAdapter(FirstActivity.this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{DISPLAY_NAME, NUMBER},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        //使用自定义的异步加载类实现异步加载数据库
        LoaderManager manager = getSupportLoaderManager();
        manager.initLoader(20,null,this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        MyLoader loader = new MyLoader(FirstActivity.this);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    public static class MyLoader extends AsyncTaskLoader<Cursor> {

        public MyLoader(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        public Cursor loadInBackground() {
            ContentResolver resolver = getContext().getContentResolver();
            Cursor cursor = resolver.query(uri, columns, null, null, null);
            return cursor;
        }

    }
}
