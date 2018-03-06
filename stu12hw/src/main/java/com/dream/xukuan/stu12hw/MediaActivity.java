package com.dream.xukuan.stu12hw;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.io.File;

public class MediaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final Uri AUDIO_URL = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private static final String _ID = MediaStore.Audio.Media._ID;
    private static final String DISPLAY_NAME = MediaStore.Audio.Media.DISPLAY_NAME;
    private static final String PATH = MediaStore.Audio.Media.DATA;
    private static final String TITLE = MediaStore.Audio.Media.TITLE;
    private ListView listView;
    SimpleCursorAdapter adapter;
    private String[] columns = {_ID,DISPLAY_NAME,PATH,TITLE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        listView = (ListView) findViewById(R.id.media_list);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(AUDIO_URL, columns, null, null, null);
        adapter = new SimpleCursorAdapter(MediaActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{TITLE,PATH},
                new int[]{R.id.text1,R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Cursor cursor = adapter.getCursor();
        int cursorPosition = cursor.getPosition();
        File file = new File(cursor.getString(cursor.getColumnIndex(PATH)));
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri,"audio/*");
        startActivity(intent);
    }
}
