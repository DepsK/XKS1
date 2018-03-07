package com.dream.xukuan.stu13;

import android.graphics.Bitmap;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HWActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Bitmap> {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw);
        imageView = (ImageView) findViewById(R.id.hw_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaderManager manager = getSupportLoaderManager();
                manager.initLoader(1,null,HWActivity.this);
            }
        });
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        String imgUrl = "http://365jia.cn/uploads/news/folder_1890088/images/f4ce434e36082e29499315617ce2e849.JPG";
        MyImageLoader loader = new MyImageLoader(HWActivity.this,imgUrl);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
        if(data!=null){
            imageView.setImageBitmap(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {
        imageView.setImageResource(R.mipmap.ic_launcher);
    }
}
