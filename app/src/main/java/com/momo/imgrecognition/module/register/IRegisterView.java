package com.momo.imgrecognition.module.register;

import android.os.Message;

/**
 * Created by Administrator on 2017/5/8.
 */

public interface IRegisterView {

    String getUsername();
    String getPassword();
    String getPhoneNumber();
    void showError(String message);
}
