package com.momo.imgrecognition.module.login.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/18.
 */

public class User implements Serializable{
    private String password;
    private String name;

    public User(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
