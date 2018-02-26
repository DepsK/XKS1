package com.dream.xukuan.stu8.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.dream.xukuan.stu8.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/2/26.
 */
public class ImageTask extends AsyncTask<String, Void, Bitmap> {

    private String urlString;
    private ImageView imageView;

    @Override
    protected Bitmap doInBackground(String... params) {
        urlString = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[3096];
            while (true){
                publishProgress();
                int ret = inputStream.read(buff, 0, buff.length);
                if (ret == -1){

                    break;
                }
                bos.write(buff,0,ret);
            }
            byte[] data = bos.toByteArray();
            return BitmapFactory.decodeByteArray(data,0,data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        if (!urlString.equals(imageView.getTag())){
            cancel(true);
        }
    }
    public ImageTask load(String url){
        execute(url);
        return this;
    }

    public void into(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap !=null){
            if(urlString.equals(imageView.getTag())){
                imageView.setImageBitmap(bitmap);
            }else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}