package com.dream.xukuan.stu9;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/2/28.
 */
public class MyImageLoader extends AsyncTask<String,Integer,Bitmap> {

    private final Context context;
    private final ImageView imageView;
    private ProgressDialog dialog;

    public MyImageLoader(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMax(100);
        dialog.setTitle("正在下载中");
        dialog.setMessage("正在下载中。。。");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageUrl = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1];
            int total = connection.getContentLength();
            int sum =0;
            int progress;
            while (true){
                int ret = inputStream.read(buff, 0, buff.length);
                if (ret == -1){
                    break;
                }
                sum+=ret;
                progress = (int) (sum*100.0/total);
                Log.d("qwe", String.valueOf(progress));
                publishProgress(progress);
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
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //更新对话框进度
        dialog.setMessage("正在下载，请稍候");
        //style为STYLE_HORIZONTAL的才有用
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap !=null){
            imageView.setImageBitmap(bitmap);
        }
        dialog.dismiss();
    }
}