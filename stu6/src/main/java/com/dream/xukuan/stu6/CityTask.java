package com.dream.xukuan.stu6;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author XK
 * @date 2018/2/6.
 */
public class CityTask extends AsyncTask<String , Void ,String> {

    public interface OnLoadCompletedListener{
         void onLoadCompleted(String result);
    }

    private OnLoadCompletedListener listener;

    public void setOnLoadCompletedListener(OnLoadCompletedListener listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String httpUrl = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(httpUrl).openConnection();
            InputStream inputStream = connection.getInputStream();
            byte[] buff = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (true){
                int ret = inputStream.read(buff,0,buff.length);
                if(ret == -1){
                    break;
                }
                bos.write(buff,0,ret);
            }
            return bos.toString();
        } catch (IOException e) {

        }

        return null;
    }



    @Override
    protected void onPostExecute(String result) {
        if(listener != null){
            listener.onLoadCompleted(result);
        }
    }
}