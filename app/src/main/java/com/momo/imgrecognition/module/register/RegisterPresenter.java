package com.momo.imgrecognition.module.register;

import android.graphics.Bitmap;
import android.support.v7.view.menu.MenuPresenter;

import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.login.biz.ILoginBiz;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.utils.BitmapUtil;
import com.momo.imgrecognition.utils.SharedUtil;
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

        // TODO: 2017/5/16 phone number is not modified
        RegisterBean bean = new RegisterBean(password,username);
        mRegisterBiz.register(bean, new RegisterListener() {
            @Override
            public void regiseteSuccess(RegisterResponse response) {
                saveData(response);
                ShowUtil.toast("注册成功！");
            }

            @Override
            public void registerFailed(String message) {
                mRegisterView.showError(message);
            }
        });

    }

    private void saveData(RegisterResponse response) {
        SharedUtil.saveParam(UserConfig.USER_NAME,response.getName());
        SharedUtil.saveParam(UserConfig.USER_ID,response.getId());
        SharedUtil.saveParam(UserConfig.USER_TOKEN,response.getToken());
        String userIconUrl = response.getAvatarUrl();
        if(userIconUrl!= null) {
            Bitmap userIcon = BitmapUtil.getHttpPicture(userIconUrl);
            BitmapUtil.savePicture(userIcon, "userIcon.jpg");
        }
    }
}
