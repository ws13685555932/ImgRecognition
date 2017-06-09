package com.momo.imgrecognition.module.category.bean;

import java.util.List;

/**
 * @author wangsheng
 * @date 2017-06-09.
 */

public class CateResponse {

    private List<PictureListBean> pictureList;

    public List<PictureListBean> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureListBean> pictureList) {
        this.pictureList = pictureList;
    }

    public static class PictureListBean {
        /**
         * pictureId : 116
         * path : http://115.159.26.94:9001/common/2017/05/21/0d70bcdcb95fa.jpg
         * type : 体育,动物,其他
         * acceptedLabel : 跑步,狗,猎物
         * labelNumber : 11
         * isFinished : 0
         * uploadTime : 1495340378940
         * recognitionLabel : []
         * managerName : 测试3
         */

        private String pictureId;
        private String path;
        private String type;
        private String acceptedLabel;
        private int labelNumber;
        private boolean isFinished;
        private String uploadTime;
        private String recognitionLabel;
        private String managerName;



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

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void setFinished(boolean finished) {
            isFinished = finished;
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

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }
    }
}
