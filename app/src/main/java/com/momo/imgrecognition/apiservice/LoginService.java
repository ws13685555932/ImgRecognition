package com.momo.imgrecognition.apiservice;

import com.momo.imgrecognition.module.login.bean.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/5/6.
 */

public interface LoginService {
    //http://115.159.26.94:3001/api/user/signIn
    @POST("user/signIn")
    Observable<ResponseInfo<LoginResponse>> login(@Body User user);

}


