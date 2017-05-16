package com.momo.imgrecognition.module.register;

import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterBiz implements IRegisterBiz{
    @Override
    public void register(final RegisterBean registerBean, final RegisterListener listener) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        Observable<ResponseInfo<RegisterResponse>> call = userService.register(registerBean);
        call.compose(RxSchedulersHelper.<ResponseInfo<RegisterResponse>>io_main())
                .subscribe(new HttpObserver<RegisterResponse>() {
                    @Override
                    public void onSuccess(RegisterResponse registerResponse) {
                        listener.regiseteSuccess(registerResponse);
                    }

                    @Override
                    public void onFailed(String message) {
                        listener.registerFailed(message);
                    }
                });
    }
}
