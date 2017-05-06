package com.momo.imgrecognition.apiservice;

/**
 {
     "success": true,
     "message": "",
     "data":{
     "id": 6,
     "email": "test5@foxmail.com",
     "name": "test5",
     "major": null,
     "marked": null,
     "create_time": "1493203225728",
     "modified_time": null,
     "score": 0,
     "level": 0,
     "token": "2a64001b-be8c-4d93-8b58-b7311ab6ea71"
     },
     "code": ""
 }
 */

public class LoginResponse{
    int id;
    String email;
    String name;
    String major;
    String marked;
    long create_time;
    int score;
    int level;
    String token;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", marked='" + marked + '\'' +
                ", create_time=" + create_time +
                ", score=" + score +
                ", level=" + level +
                ", token='" + token + '\'' +
                '}';
    }
}
