package com.momo.imgrecognition.module.login.biz;

import android.util.Log;

import com.momo.imgrecognition.apiservice.Config;
import com.momo.imgrecognition.apiservice.LoginResponse;
import com.momo.imgrecognition.apiservice.LoginService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/4/18.
 */

public class LoginBiz implements ILoginBiz {
    @Override
    public void login(final User user, final OnLoginListener loginListener) {
        LoginService loginService = HttpManager.getInstance().createService(LoginService.class);
        Observable<ResponseInfo<LoginResponse>> call = loginService.login(user);
        call.compose(RxSchedulersHelper.<ResponseInfo<LoginResponse>>io_main())
                .subscribe(new HttpObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        loginListener.loginSuccess(user);
                    }

                    @Override
                    public void onFailed(String message) {
                        loginListener.loginFailed(message);
                    }
                });
    }
}
