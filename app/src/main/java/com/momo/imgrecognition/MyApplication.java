package com.momo.imgrecognition;

import android.app.Application;
import android.content.Context;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by Administrator on 2017/4/21.
 */

public class MyApplication extends Application {

        private static Context context;

        @Override
        public void onCreate() {
            super.onCreate();
            SkinConfig.setCanChangeStatusColor(true);
//            SkinConfig.addSupportAttr("progressBar", new ProgressBarAttr());
            context = getApplicationContext();
        }

        public static Context getContext(){
            return context;
        }

}
