package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class SexBean {
    String id;
    String sex;
    String token;

    public String getSex() {
        return sex;
    }

    public SexBean(String sex) {

        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
