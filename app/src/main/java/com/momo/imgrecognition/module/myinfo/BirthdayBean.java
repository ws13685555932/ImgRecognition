package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BirthdayBean {
    int id;
    String birthday;
    String token;

    public BirthdayBean(String birthday) {
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setBirthday(String birthday) {

        this.birthday = birthday;
    }

    public String getBirthday() {

        return birthday;
    }
}
