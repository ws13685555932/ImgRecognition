package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/16.
 */

public class DescriptionBean {
    int id;
    String introduction;
    String token;

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DescriptionBean(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
