package com.momo.imgrecognition.module.login.presenter;

import com.momo.imgrecognition.module.login.biz.ILoginBiz;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.view.ILoginView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class LoginPresenter {
    private ILoginBiz mLoginBiz;
    private ILoginView mLoginView;

    public LoginPresenter(ILoginView loginView) {
        this.mLoginView = loginView;
        mLoginBiz = new LoginBiz();
    }


    public void login() {
        mLoginView.toMainActivity();
    }
}
