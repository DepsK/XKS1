package com.dream.xukuan.stu15;

import android.content.Context;
import android.content.res.Configuration;

/**
 * @author XK
 * @date 2018/3/9.
 */
public class MyUtils {
    public static  Boolean isLand(Context context){
        //是否横屏
       return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
} 