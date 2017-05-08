package com.momo.imgrecognition.module.register;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterBean {
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
//    private String phone;

    public RegisterBean(String password, String name) {
        this.password = password;
        this.confirmPassword = password;
        this.name = name;
        this.email = "1748532294@qq.com";
//        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
