package com.momo.imgrecognition.module.register;

/**
 * Created by Administrator on 2017/5/8.
 */

public interface RegisterListener {
    void regiseteSuccess(RegisterResponse response);
    void registerFailed(String message);
}
