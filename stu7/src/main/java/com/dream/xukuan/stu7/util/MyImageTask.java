package com.dream.xukuan.stu7.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.dream.xukuan.stu7.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/2/10.
 */
public class MyImageTask extends AsyncTask<String,Void,Bitmap>{
    private  ImageView imageView;
    private String loadUrlString;

    public MyImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        loadUrlString = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(loadUrlString).openConnection();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            BufferedInputStream bis = new BufferedInputStream(inputStream);

            byte[] buff = new byte[2048];
            //如果开启了一个新的任务为当前imageView下载图片的时候，之前的任务如果还没完成，那就不需要继续
            //判断此异步任务有没有必要继续:如果doInBackground方法的参数url和imageView标注的tag不一致（说明imageView已经被复用到另外的item）
            while (true) {
                //此异步任务没有必要继续
             /*   if (!loadUrlString .equals(imageView.getTag())) {
                    //停止异步任务
                    cancel(true);
                    Log.e("print", "取消异步任务.....");
                }*/
                int ret = bis.read(buff, 0, buff.length);
                if (ret == -1) {
                    break;
                }
                bos.write(buff,0,ret);
            }
            byte[] data = bos.toByteArray();
            if (data == null) {
                return null;
            }

            return BitmapFactory.decodeByteArray(data, 0, data.length);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("print", "网络超时.....");
        }

        return null;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            //判断这个异步任务下载到的图片，和现在这个imageView需要显示的图片是不是同一张
            if ( loadUrlString.equals(imageView.getTag())) {
                imageView.setImageBitmap(result);
            }else {
                Log.e("print", "图片错位了！");
            }
        }
        else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }
}