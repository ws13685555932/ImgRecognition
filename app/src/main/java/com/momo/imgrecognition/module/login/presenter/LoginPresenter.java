package com.momo.imgrecognition.module.login.presenter;

import android.widget.Toast;

import com.momo.imgrecognition.MyApplication;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.login.biz.ILoginBiz;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.biz.OnLoginListener;
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
//        mLoginView.toMainActivity();
        String name = mLoginView.getUsername();
        String password = mLoginView.getPassword();
        User user = new User(password,name);
        mLoginBiz.login(user, new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                Toast.makeText(MyApplication.getContext(), "login sucess!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginFailed(String message) {
                Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
