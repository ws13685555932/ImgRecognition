package com.momo.imgrecognition.module.register;

/**
 * Created by Administrator on 2017/5/8.
 */

public interface RegisterListener {
    void regiseteSuccess(RegisterBean registerBean);
    void registerFailed(String message);
}
