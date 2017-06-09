package com.momo.imgrecognition.module.category;

/**
 * Created by Administrator on 2017/5/25.
 */

public class CateImageBean {
    String imgUrl;
    String picId;
    String tagCont;
    String tagAdmin;
    int tagNum;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getTagCont() {
        return tagCont;
    }

    public void setTagCont(String tagCont) {
        this.tagCont = tagCont;
    }

    public String getTagAdmin() {
        return tagAdmin;
    }

    public void setTagAdmin(String tagAdmin) {
        this.tagAdmin = tagAdmin;
    }

    public int getTagNum() {
        return tagNum;
    }

    public void setTagNum(int tagNum) {
        this.tagNum = tagNum;
    }

    @Override
    public String toString() {
        return "CateImageBean{" +
                "imgUrl='" + imgUrl + '\'' +
                ", picId='" + picId + '\'' +
                ", tagCont='" + tagCont + '\'' +
                ", tagAdmin='" + tagAdmin + '\'' +
                ", tagNum=" + tagNum +
                '}';
    }
}
