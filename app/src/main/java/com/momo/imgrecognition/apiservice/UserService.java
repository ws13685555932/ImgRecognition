package com.momo.imgrecognition.apiservice;

import com.momo.imgrecognition.module.history.HistoryRequest;
import com.momo.imgrecognition.module.history.HistoryResponse;
import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.main.bean.RecomResponse;
import com.momo.imgrecognition.module.myinfo.BirthdayBean;
import com.momo.imgrecognition.module.myinfo.DescriptionBean;
import com.momo.imgrecognition.module.myinfo.EmailBean;
import com.momo.imgrecognition.module.myinfo.InfoBean;
import com.momo.imgrecognition.module.myinfo.UpdateResponse;
import com.momo.imgrecognition.module.myinfo.NameBean;
import com.momo.imgrecognition.module.myinfo.SexBean;
import com.momo.imgrecognition.module.myinfo.UrlBean;
import com.momo.imgrecognition.module.myinfo.UserInfo;
import com.momo.imgrecognition.module.myinfo.UserRequest;
import com.momo.imgrecognition.module.mymessage.MessageList;
import com.momo.imgrecognition.module.mymessage.MessageRequest;
import com.momo.imgrecognition.module.register.PhoneBean;
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

    @POST("user/update")
    Observable<ResponseInfo<UpdateResponse>> updateName(@Body NameBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<UpdateResponse>> updateEmail(@Body EmailBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<UpdateResponse>> updateDescription(@Body DescriptionBean myInfoBean);

    @POST("user/update")
    Observable<ResponseInfo<UpdateResponse>> updateBirthday(@Body BirthdayBean birthdayBean);

    @POST("user/update")
    Observable<ResponseInfo<UpdateResponse>> updateSex(@Body SexBean sexBean);

    @POST("user/exitPhone")
    Observable<ResponseInfo<Object>> isPhoneExist(@Body PhoneBean sexBean);

    @POST("user/getUserMessage")
    Observable<ResponseInfo<MessageList>> getUserMessage(@Body MessageRequest request);

    @POST("user/getUserInfo")
    Observable<ResponseInfo<UserInfo>> getUserInfo(@Body UserRequest request);

    @POST("user/updateUserAvatar")
    Observable<ResponseInfo<UpdateResponse>> updateUserAvatar(@Body UrlBean urlBean);

    @POST("user/getHistoryLabel")
    Observable<ResponseInfo<HistoryResponse>> getHistoryLabel(@Body HistoryRequest request);


}


