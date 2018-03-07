package com.dream.xukuan.stu13;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SecondActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SearchView searchView;
    ListView listView;
    SimpleCursorAdapter adapter;
    private static final Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final String _ID = ContactsContract.CommonDataKinds.Phone._ID;
    private static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    private static final String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    static String[] columns = {_ID, DISPLAY_NAME, NUMBER};
    private LoaderManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        searchView = (SearchView) findViewById(R.id.search_view);
        setSearchView();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new SimpleCursorAdapter(SecondActivity.this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{DISPLAY_NAME, NUMBER},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        //开始异步加载：
        //1.获得异步加载的管理器
        manager = getSupportLoaderManager();
        manager.initLoader(20, null, this);
    }

    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    manager.restartLoader(10, null, SecondActivity.this);
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putString("key", newText);
                    manager.restartLoader(10, bundle, SecondActivity.this);
                }
                return false;
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = null;
        if (args == null) {
            loader = new CursorLoader(SecondActivity.this, uri, columns, null, null, null);
        } else {
            String key = args.getString("key");
            loader = new CursorLoader(SecondActivity.this, uri, columns, DISPLAY_NAME + "like ?", new String[]{"%" + key + "%"}, null);
        }
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
}
