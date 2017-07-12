package com.momo.imgrecognition.module.detail.bean;

import java.util.List;

/**
 * @author wangsheng
 * @date 2017-06-14.
 */

public class LabelResponse {
    /**
     * data : {"labelList":[{"labelId":48,"userId":3,"pictureId":142,"label":"测试","type":"其他"}]}
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
             * labelId : 48
             * userId : 3
             * pictureId : 142
             * label : 测试
             * type : 其他
             */

//            private String labelId;
//            private String userId;
//            private String pictureId;
            private String label;
//            private String type;


            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }
        }
}
