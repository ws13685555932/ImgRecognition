package com.momo.imgrecognition.module.search;

import java.util.List;

/**
 * @author wangsheng
 * @date 2017-06-11.
 */

public class SearchReponse {


    /**
     * data : {"pictureList":[{"pictureId":48,"path":"http://115.159.26.94:9001/common\\2017\\05\\10\\ea730cbf5b24e.jpg","type":null,"acceptedLabel":"小猫","labelNumber":0,"isFinished":"0","uploadTime":"1494412906942","recognitionLabel":null,"managerName":"测试3"}]}
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
         * pictureId : 48
         * path : http://115.159.26.94:9001/common\2017\05\10\ea730cbf5b24e.jpg
         * type : null
         * acceptedLabel : 小猫
         * labelNumber : 0
         * isFinished : 0
         * uploadTime : 1494412906942
         * recognitionLabel : null
         * managerName : 测试3
         */

        private String pictureId;
        private String path;
        private String type;
        private String acceptedLabel;
        private int labelNumber;
        private String isFinished;
        private String uploadTime;
        private String recognitionLabel;
        private String managerName;

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
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

        @Override
        public String toString() {
            return "PictureListBean{" +
                    "pictureId='" + pictureId + '\'' +
                    ", path='" + path + '\'' +
                    ", type='" + type + '\'' +
                    ", acceptedLabel='" + acceptedLabel + '\'' +
                    ", labelNumber=" + labelNumber +
                    ", isFinished='" + isFinished + '\'' +
                    ", uploadTime='" + uploadTime + '\'' +
                    ", recognitionLabel='" + recognitionLabel + '\'' +
                    ", managerName='" + managerName + '\'' +
                    '}';
        }
    }

}
