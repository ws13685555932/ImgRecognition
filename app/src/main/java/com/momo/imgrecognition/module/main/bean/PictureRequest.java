package com.momo.imgrecognition.module.main.bean;

/**
 * Created by Administrator on 2017/5/24.
 */

public class PictureRequest {
    /**
     * id : 12
     * limit : 20
     */

    private String id;
    private int limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
