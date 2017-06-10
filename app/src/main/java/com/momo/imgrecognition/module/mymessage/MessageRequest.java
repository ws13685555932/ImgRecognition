package com.momo.imgrecognition.module.mymessage;

/**
 * Created by Administrator on 2017/5/22.
 */

public class MessageRequest {
    String id;
    String token;

    public MessageRequest() {
    }

    public MessageRequest(String id, String token) {
        this.id = id;
        this.token = token;
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

    public void setToken(String token) {
        this.token = token;
    }
}
