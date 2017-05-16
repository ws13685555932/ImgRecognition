package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class NameBean {
    int id;
    String name;
    String token;

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NameBean(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
