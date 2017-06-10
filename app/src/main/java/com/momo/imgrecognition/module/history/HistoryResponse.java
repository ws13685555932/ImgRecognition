package com.momo.imgrecognition.module.history;

import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public class HistoryResponse {

    /**
     * data : {"labelList":[{"id":9,"userId":3,"pictureId":115,"label":"大狗","type":"动物","weight":3,"created_time":"1495564021572","managerId":null,"planId":null}]}
     */

    private List<LabelListBean> labelList;

    public List<LabelListBean> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelListBean> labelList) {
        this.labelList = labelList;
    }

    public static class LabelListBean {
        /**
         * id : 9
         * userId : 3
         * pictureId : 115
         * label : 大狗
         * type : 动物
         * weight : 3
         * created_time : 1495564021572
         * managerId : null
         * planId : null
         */

        private String id;
        private String userId;
        private String pictureId;
        private String label;
        private String type;
        private int weight;
        private String created_time;
        private String managerId;
        private String planId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getManagerId() {
            return managerId;
        }

        public void setManagerId(String managerId) {
            this.managerId = managerId;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }
    }

}
