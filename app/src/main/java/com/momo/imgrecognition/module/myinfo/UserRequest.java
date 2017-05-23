package com.momo.imgrecognition.module.myinfo;

/**
 * Created by Administrator on 2017/5/23.
 */

public class UserRequest {
    int id;
    String name;
    String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
