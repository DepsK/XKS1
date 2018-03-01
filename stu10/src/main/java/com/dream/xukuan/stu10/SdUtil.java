package com.dream.xukuan.stu10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author XK
 * @date 2018/3/1.
 */
public class SdUtil  {

    public static Bitmap readBitmap(String imgUrl){
        if(exit(imgUrl)){
            File file = getFile(imgUrl);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        }
        return null;
    }

    //判断文件是否存在
    private static boolean exit(String imgUrl) {
        File file = getFile(imgUrl);
        return file.exists();
    }
    //获得图片文件对象
    private static File getFile(String imgUrl) {
        File dir = null;
        //如果sd卡存在，文件就保存到外部存储
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }else {
            //不存在就保存内部存储
            dir = Environment.getDownloadCacheDirectory();
        }
        String fileName = imgUrl;
        File file = new File(dir,fileName);
        Log.d("qwe", "getFile: "+file.getAbsolutePath());
        return file;
    }

    private static String caculate(String imgUrl) {
        return "save.jpg";
    }

    public static void writeBitmap(String imgUrl,Bitmap bitmap){
        File file = getFile(imgUrl);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
            bitmap.compress(format,100,fos);
        } catch (FileNotFoundException e) {

        }
    }
} 