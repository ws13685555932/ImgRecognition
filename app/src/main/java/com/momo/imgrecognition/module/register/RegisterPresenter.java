package com.momo.imgrecognition.module.register;

import android.support.v7.view.menu.MenuPresenter;

import com.momo.imgrecognition.module.login.biz.ILoginBiz;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.utils.ShowUtil;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterPresenter {
    private IRegisterBiz mRegisterBiz;
    private IRegisterView mRegisterView;

    public RegisterPresenter(IRegisterView registerView) {
        this.mRegisterView = registerView;
        mRegisterBiz = new RegisterBiz();
    }

    public void register(){
        String username  = mRegisterView.getUsername();
        String password = mRegisterView.getPassword();
        String phoneNumber = mRegisterView.getPhoneNumber();

        RegisterBean bean = new RegisterBean(password,username);
        mRegisterBiz.register(bean, new RegisterListener() {
            @Override
            public void regiseteSuccess(RegisterBean registerBean) {
                ShowUtil.toast("注册成功！");
            }

            @Override
            public void registerFailed(String message) {
                mRegisterView.showError(message);
            }
        });

    }
}
