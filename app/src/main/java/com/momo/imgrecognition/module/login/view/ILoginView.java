package com.momo.imgrecognition.module.login.view;

import com.momo.imgrecognition.module.login.bean.LoginResponse;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface ILoginView {
    String getUsername();
    String getPassword();

    void toMainActivity(int level, String iconUrl, String name);
    void toRegisterActivity();
    void showLoadDialog();
    void dismissLoadDialog();
    void saveId(String id);
}
