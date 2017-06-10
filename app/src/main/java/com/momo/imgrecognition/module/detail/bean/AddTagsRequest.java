package com.momo.imgrecognition.module.detail.bean;

/**
 * @author wangsheng
 * @date 2017-06-10.
 */

public class AddTagsRequest {
    /**
     * id : 3
     * pictureId : 47
     * label : 大狗
     */

    private String id;
    private String pictureId;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
