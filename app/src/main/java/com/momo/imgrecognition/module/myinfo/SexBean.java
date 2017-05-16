package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class SexBean {
    int id;
    String sex;
    String token;

    public String getSex() {
        return sex;
    }

    public SexBean(String sex) {

        this.sex = sex;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
