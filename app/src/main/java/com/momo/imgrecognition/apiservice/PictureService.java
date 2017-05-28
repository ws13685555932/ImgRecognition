package com.momo.imgrecognition.apiservice;

import android.icu.text.Replaceable;

import com.momo.imgrecognition.module.main.bean.PictureRequest;
import com.momo.imgrecognition.module.main.bean.RecomResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/5/22.
 */

public interface PictureService {

    @POST("picture/getPicture")
    Observable<ResponseInfo<RecomResponse>> getPicture(@Body PictureRequest request);

}
