package com.dream.xukuan.stu5;

import android.app.Application;

import java.util.HashMap;

/**
 * @author XK
 * @date 2018/2/5.
 */
public class MyApp extends Application {

    private HashMap<String,String> userMap;
    @Override
    public void onCreate() {
        super.onCreate();
        this.userMap = new HashMap<String,String>();
    }

    public HashMap<String, String> getUserMap() {
        return userMap;
    }
}