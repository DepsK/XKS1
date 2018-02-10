package com.dream.xukuan.stu7.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
public class HttpUtil {


    public static byte[] loadByte(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] buff = new byte[1024];
            while (true){
                int ret = bis.read(buff,0,buff.length);
                if(ret == -1){
                    break;
                }
                bos.write(buff,0,ret);
            }
            return bos.toByteArray();
        } catch (IOException e) {

        }
        return null;
    }
    public static String loadJson(String urlString) {
        byte[] data = loadByte(urlString);
        if (data == null) {
            return null;
        }
        return new String(data);
    }

    public static Bitmap LoadImage(String urlString) {
        byte[] data = loadByte(urlString);
        if (data != null) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        return null;
    }
}