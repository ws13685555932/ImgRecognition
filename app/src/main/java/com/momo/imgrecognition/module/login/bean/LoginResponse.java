package com.momo.imgrecognition.module.login.bean;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
