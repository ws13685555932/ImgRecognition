package com.momo.imgrecognition.apiservice;

import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.register.RegisterBean;
import com.momo.imgrecognition.module.register.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/5/6.
 */

public interface UserService {
    //http://115.159.26.94:3001/api/user/signIn
    @POST("user/signIn")
    Observable<ResponseInfo<LoginResponse>> login(@Body User user);

    @POST("user/signUp")
    Observable<ResponseInfo<RegisterResponse>> register(@Body RegisterBean registerBean);

}


