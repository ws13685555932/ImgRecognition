package com.momo.imgrecognition.module.login.biz;

import com.momo.imgrecognition.module.login.bean.User;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface ILoginBiz {
    void login(User user, OnLoginListener loginListener);
}
