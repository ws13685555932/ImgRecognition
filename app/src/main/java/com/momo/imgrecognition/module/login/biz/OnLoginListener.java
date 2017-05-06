package com.momo.imgrecognition.module.login.biz;

import com.momo.imgrecognition.module.login.bean.User;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed(String msg);
}
