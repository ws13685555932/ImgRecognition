package com.momo.imgrecognition.module.register;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterResponse {
    private String password;
    private String name;
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
