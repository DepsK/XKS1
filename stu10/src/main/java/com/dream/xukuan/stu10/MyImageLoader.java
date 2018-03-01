package com.dream.xukuan.stu10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/3/1.
 */
public class MyImageLoader extends AsyncTask<String,String,Bitmap> {


    private final Context context;
    private ImageView imageView;

    public MyImageLoader(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imgUrl = params[0];
        //从sd来读取图片；如果图片存在，直接使用，不存在再开始下载
        Bitmap bitmap = null;
        bitmap = SdUtil.readBitmap(imgUrl);
        if (bitmap != null) {
            publishProgress("从文件缓存中得到图片！");
        }else {
            publishProgress("网路下载图片！");
        }
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
            InputStream inputStream = connection.getInputStream();
            /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            while (true){
                int ret = inputStream.read(buff,0,buff.length);
                if(ret == -1){
                    break;
                }
                bos.write(buff,0,ret);
            }
            byte[] data = bos.toByteArray();*/
            bitmap = BitmapFactory.decodeStream(inputStream);
            SdUtil.writeBitmap(imgUrl,bitmap);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context,values[0],Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap !=null){
            imageView.setImageBitmap(bitmap);
        }
    }
    public void into(ImageView imageView){
        this.imageView = imageView;
    }

    public MyImageLoader load(String imgUrl){
        execute(imgUrl);
        return this;
    }
}