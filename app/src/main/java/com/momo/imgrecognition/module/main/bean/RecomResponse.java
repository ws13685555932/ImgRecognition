package com.momo.imgrecognition.module.main.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class RecomResponse {
    /**
     * data : {"pictureList":[{"id":67,"managerId":9,"path":"http://115.159.26.94:9001/common\\2017\\05\\10\\2b3b30de4e6e5.jpg","type":null,"planId":1,"acceptedLabel":null,"labelNumber":0,"isFinished":"0","resultLabel":null,"uploadTime":"1494412908810","recognitionLabel":null}]}
     */
    private List<PictureListBean> pictureList;

    public List<PictureListBean> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureListBean> pictureList) {
        this.pictureList = pictureList;
    }

    public static class PictureListBean {
        /**
         * id : 67
         * managerId : 9
         * path : http://115.159.26.94:9001/common\2017\05\10\2b3b30de4e6e5.jpg
         * type : null
         * planId : 1
         * acceptedLabel : null
         * labelNumber : 0
         * isFinished : 0
         * resultLabel : null
         * uploadTime : 1494412908810
         * recognitionLabel : null
         */

        private int id;
        private int managerId;
        private String path;
        private String type;
        private int planId;
        private String acceptedLabel;
        private int labelNumber;
        private String isFinished;
        private String resultLabel;
        private String uploadTime;
        private String recognitionLabel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
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

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
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

}
