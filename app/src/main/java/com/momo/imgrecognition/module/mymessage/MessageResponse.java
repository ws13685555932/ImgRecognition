package com.momo.imgrecognition.module.mymessage;

/**
 * Created by Administrator on 2017/5/22.
 */

// "id": 1,
//         "userId": 3,
//         "managerId": null,
//         "type": "系统",
//         "created_time": "1111111111",
//         "title": "test",
//         "message": "这是一个测试message的数据",
//         "isWatched": 0



public class MessageResponse {
    int id;
    int userId;
    int managerId;
    String type;
    long created_time;
    String title;
    String message;
    int isWatched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsWatched() {
        return isWatched;
    }

    public void setIsWatched(int isWatched) {
        this.isWatched = isWatched;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", managerId=" + managerId +
                ", type='" + type + '\'' +
                ", created_time=" + created_time +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", isWatched=" + isWatched +
                '}';
    }
}
