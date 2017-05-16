package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class EmailBean {
    int id;
    String email;
    String token;

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EmailBean(String email) {
        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
