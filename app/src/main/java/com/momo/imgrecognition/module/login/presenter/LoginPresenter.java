package com.momo.imgrecognition.module.login.presenter;

import android.widget.Toast;

import com.momo.imgrecognition.MyApplication;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.login.biz.ILoginBiz;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.biz.OnLoginListener;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.utils.SharedUtil;

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
//        mLoginView.showLoadDialog();
        if(Config.IS_TEST){
            mLoginView.toMainActivity();
        }else {
            String name = mLoginView.getUsername();
            String password = mLoginView.getPassword();
            User user = new User(password, name);
            mLoginBiz.login(user, new OnLoginListener() {
                @Override
                public void loginSuccess(LoginResponse response) {
//                mLoginView.dismissLoadDialog();
                    saveData(response);
                    Toast.makeText(MyApplication.getContext(), "login sucess!", Toast.LENGTH_SHORT).show();
                    mLoginView.toMainActivity();
                }

                @Override
                public void loginFailed(String message) {
//                mLoginView.dismissLoadDialog();
                    Toast.makeText(MyApplication.getContext(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveData(LoginResponse response) {
        SharedUtil.saveParam(UserConfig.USER_ID,response.getId());
        SharedUtil.saveParam(UserConfig.USER_NAME,response.getName());
        SharedUtil.saveParam(UserConfig.USER_TOKEN, response.getToken());
    }
}
