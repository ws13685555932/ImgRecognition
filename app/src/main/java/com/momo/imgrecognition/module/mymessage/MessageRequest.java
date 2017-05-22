package com.momo.imgrecognition.module.mymessage;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MessageRequest {
    int id;
    String token;

    public MessageRequest() {
    }

    public MessageRequest(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
