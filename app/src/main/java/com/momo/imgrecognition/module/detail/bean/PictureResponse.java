package com.momo.imgrecognition.module.detail.bean;

/**
 * @author wangsheng
 * @date 2017-06-09.
 */

public class PictureResponse {


    /**
     * id : 47
     * managerId : 9
     * path : http://115.159.26.94:9001/common\2017\05\10\84fc0fe12cdf1.jpg
     * type : null
     * planId : 1
     * acceptedLabel : 小鱼，小狗
     * labelNumber : 2
     * isFinished : 1
     * resultLabel : null
     * uploadTime : 1494412905926
     * recognitionLabel : null
     */

    private String id;
    private String managerId;
    private String path;
    private String type;
    private String planId;
    private String acceptedLabel;
    private int labelNumber;
    private String isFinished;
    private String resultLabel;
    private String uploadTime;
    private String recognitionLabel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getAcceptedLabel() {
        return acceptedLabel;
    }

    public void setAcceptedLabel(String acceptedLabel) {
        this.acceptedLabel = acceptedLabel;
    }

    public int getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(int labelNumber) {
        this.labelNumber = labelNumber;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public String getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(String resultLabel) {
        this.resultLabel = resultLabel;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getRecognitionLabel() {
        return recognitionLabel;
    }

    public void setRecognitionLabel(String recognitionLabel) {
        this.recognitionLabel = recognitionLabel;
    }
}
