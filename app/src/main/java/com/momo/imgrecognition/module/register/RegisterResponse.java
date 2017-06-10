package com.momo.imgrecognition.module.register;

/**
 * Created by Administrator on 2017/5/8.
 */

public class RegisterResponse {
    String id;
    String email;
    String name;
    String avatarUrl;
    //// TODO: 2017/5/16 sex is int? or string?
    String sex;
    String birthday;
    String major;
    // TODO: 2017/5/16 marked is?
    String marked;
    String introduction;
    long create_time;
    int score;
    int level;
    String phone;
    String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
//    id": 15,
//            "email": null,
//            "name": "wangsheng",
//            "avatarUrl": null,
//            "sex": null,
//            "birthday": null,
//            "major": null,
//            "marked": null,
//            "introduction": null,
//            "create_time": "1494897199808",
//            "modified_time": null,
//            "score": 0,
//            "level": 0,
//            "phone": "13685555932",
//            "token": "c81d71d4-c6bc-448c-90a9-bbbac106d36c"
}
