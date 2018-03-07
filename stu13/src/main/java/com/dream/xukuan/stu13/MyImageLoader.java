package com.dream.xukuan.stu13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/3/7.
 */
public class MyImageLoader extends AsyncTaskLoader<Bitmap> {

    private final String imgUrl;

    public MyImageLoader(Context context, String imgUrl) {
        super(context);
        this.imgUrl = imgUrl;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Bitmap loadInBackground() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}