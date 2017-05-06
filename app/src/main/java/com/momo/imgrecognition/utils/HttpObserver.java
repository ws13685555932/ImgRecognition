package com.momo.imgrecognition.utils;


import android.util.Log;

import com.momo.imgrecognition.apiservice.ResponseInfo;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2017/5/6.
 */

public abstract class HttpObserver<T> implements Observer<ResponseInfo<T>> {
    private static final String TAG = "HttpObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(ResponseInfo<T> responseInfo) {
        if(responseInfo.getSuccess()){
            onSuccess(responseInfo.getData());
        }else{
            onFailed(responseInfo.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
        e.printStackTrace();

        String msg;
        if (e instanceof UnknownHostException) {
            msg = "没有网络";
        } else if (e instanceof SocketTimeoutException) {
            // 超时
            msg = "请求超时";
        }else{
            msg = "请求失败，请稍后重试";
        }
        onFailed(msg);

    }

    public abstract void onSuccess(T t);
    public abstract void onFailed(String message);

}
