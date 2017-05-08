package com.momo.imgrecognition.module.login.biz;

import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;


import io.reactivex.Observable;


/**
 * Created by Administrator on 2017/4/18.
 */

public class LoginBiz implements ILoginBiz {
    @Override
    public void login(final User user, final OnLoginListener loginListener) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        Observable<ResponseInfo<LoginResponse>> call = userService.login(user);
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
