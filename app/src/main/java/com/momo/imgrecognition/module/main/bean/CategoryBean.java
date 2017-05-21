package com.momo.imgrecognition.module.main.bean;

/**
 * Created by Administrator on 2017/5/21.
 */

public class CategoryBean {
    private int resId;
    private String category;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryBean(int resId, String category) {

        this.resId = resId;
        this.category = category;
    }
}
