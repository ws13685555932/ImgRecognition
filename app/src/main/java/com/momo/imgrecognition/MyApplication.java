package com.momo.imgrecognition;

import android.app.Application;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.VisibleForTesting;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.momo.imgrecognition.utils.ThemeHelper;

/**
 * @author xyczero
 * @time 16/5/2
 */
public class MyApplication extends Application  {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

}
