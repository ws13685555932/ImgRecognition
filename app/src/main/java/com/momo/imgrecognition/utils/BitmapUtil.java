package com.momo.imgrecognition.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Environment;
import android.util.Log;

import com.momo.imgrecognition.apiservice.DownloadService;
import com.momo.imgrecognition.config.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BitmapUtil {
//    public static Bitmap getHttpPicture(String url){
//        Bitmap bitmap = null;
//        try {
//            URL bitmapUrl = new URL(url);
//            InputStream inputStream = bitmapUrl.openStream();
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            inputStream.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }

    public static void downloadUserIcon(String url){
        downloadUserIcon(url,null);
    }
    public static void downloadUserIcon(String url,DownloadListener listener){
        downloadPicture(url,Config.TEMP_FILE_PATH,listener);
    }

    public static void downloadPicture(String url , final String destPath, final DownloadListener listener){
        DownloadService downloadService = HttpManager.getInstance()
                .createService(DownloadService.class);
        Observable<ResponseBody> call =  downloadService.downloadFileWithUrl(url);
        call.subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(@NonNull ResponseBody responseBody) throws Exception {
                        String filePath = writeResponseBodyToDisk(responseBody,destPath);
                        return filePath;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String path) throws Exception {
//                        Log.d(TAG, "accept: " + path);
                        if(listener != null) {
                            listener.downloadSuccess(path);
                        }
                    }
                });
    }

    private static String writeResponseBodyToDisk(ResponseBody body , String destPath) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(destPath,"userIcon.jpeg");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

//                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return futureStudioIconFile.toString();
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static void savePicture(Bitmap bitmap , String filePath , String name){
        String path = filePath;
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

    public interface DownloadListener{
        void downloadSuccess(String path);
        void downloadFailed();
    }

    private DownloadListener listener;

    public void setDownLoadListener(DownloadListener listener){
        this.listener = listener;
    }

}
