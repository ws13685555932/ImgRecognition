package com.momo.imgrecognition.module.login.biz;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface IUserLoginBiz {
    void login(String username,String password,OnLoginListener loginListener);
}
