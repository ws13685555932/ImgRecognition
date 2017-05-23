package com.momo.imgrecognition.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.momo.imgrecognition.config.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BitmapUtil {
    public static Bitmap getHttpPicture(String url){
        Bitmap bitmap = null;
        try {
            URL bitmapUrl = new URL(url);
            InputStream inputStream = bitmapUrl.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void savePicture(Bitmap bitmap ,String name){
        String path = Config.TEMP_FILE_PATH;
        File file = new File(path,name);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
