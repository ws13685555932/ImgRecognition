package com.momo.imgrecognition.apiservice;

import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.myinfo.BirthdayBean;
import com.momo.imgrecognition.module.myinfo.DescriptionBean;
import com.momo.imgrecognition.module.myinfo.EmailBean;
import com.momo.imgrecognition.module.myinfo.MyInfoBean;
import com.momo.imgrecognition.module.myinfo.NameBean;
import com.momo.imgrecognition.module.myinfo.SexBean;
import com.momo.imgrecognition.module.mymessage.MessageRequest;
import com.momo.imgrecognition.module.mymessage.MessageResponse;
import com.momo.imgrecognition.module.register.PhoneBean;
import com.momo.imgrecognition.module.register.RegisterBean;
import com.momo.imgrecognition.module.register.RegisterResponse;

import java.util.ArrayList;
import java.util.List;

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

    @POST("user/update")
    Observable<ResponseInfo<MyInfoBean>> updateName(@Body NameBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<MyInfoBean>> updateEmail(@Body EmailBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<MyInfoBean>> updateDescription(@Body DescriptionBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<MyInfoBean>> updateBirthday(@Body BirthdayBean birthdayBean);

    @POST("user/update")
    Observable<ResponseInfo<MyInfoBean>> updateSex(@Body SexBean sexBean);

    @POST("/user/exitPhone")
    Observable<ResponseInfo<Object>> isPhoneExist(@Body PhoneBean sexBean);


    // TODO: 2017/5/22 this interface has some questions
    @POST("/user/getUserMessage")
    Observable<ResponseInfo<MessageResponse>> getUserMessage(@Body MessageRequest request);



}


