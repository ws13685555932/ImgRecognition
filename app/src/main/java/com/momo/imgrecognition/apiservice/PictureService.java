package com.momo.imgrecognition.apiservice;

import android.icu.text.Replaceable;
import android.text.style.RelativeSizeSpan;

import com.momo.imgrecognition.module.category.bean.CateRequest;
import com.momo.imgrecognition.module.category.bean.CateResponse;
import com.momo.imgrecognition.module.detail.bean.IdRequest;
import com.momo.imgrecognition.module.detail.bean.PictureResponse;
import com.momo.imgrecognition.module.main.bean.PictureRequest;
import com.momo.imgrecognition.module.main.bean.RecomResponse;
import com.momo.imgrecognition.module.search.SearchReponse;
import com.momo.imgrecognition.module.search.SearchRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/5/22.
 */

public interface PictureService {

    @POST("picture/getPicture")
    Observable<ResponseInfo<RecomResponse>> getPicture(@Body PictureRequest request);

    @POST("picture/getPictureById")
    Observable<ResponseInfo<PictureResponse>> getPictureById(@Body IdRequest request);

    @POST("picture/getPictureByType")
    Observable<ResponseInfo<CateResponse>> getPictureByType(@Body CateRequest request);

    @POST("picture/searchPicture")
    Observable<ResponseInfo<SearchReponse>> searchPicture(@Body SearchRequest request);
}
