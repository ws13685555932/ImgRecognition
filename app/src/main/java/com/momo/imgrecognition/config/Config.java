package com.momo.imgrecognition.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/5/6.
 */

public class Config {
    public static final String BASE_URL = "http://115.159.26.94:3001/api/";

    public static final boolean IS_TEST = false;

    public static final String SMS_SDK_KEY = "1da26b3dcc298";
    public static final String SMS_SDK_SECRET = "60acd6a20194d6a06e638bfa41370f6d";


    public static boolean isRecieveSysMsg = true;
    public static boolean isCloseAnim = false;
    public static boolean isOnlyWifiDown = false;


    public static final String TYPE_NAME = "name";
    public static final String TYPE_EMAIL = "email";
    public static final String TYPE_DESCRIPTION = "description";


    public static final String SD_CARD = Environment.getExternalStorageDirectory().toString();
    public static final String PACKAGE_NAME = "/com.momo.imgrecognition";
    public static final String DOWNLOAD_IMG_PATH = SD_CARD + PACKAGE_NAME + "/downloadImg";

    // TODO: 2017/5/22  在哪用到的？
    public static final String TEMP_FILE_PATH = SD_CARD + PACKAGE_NAME + "/tempFile";





}
