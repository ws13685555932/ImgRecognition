package com.momo.imgrecognition.module.category.bean;

/**
 * @author wangsheng
 * @date 2017-06-09.
 */

public class CateRequest {

    /**
     * type : 体育
     * limit : 20
     * page : 1
     */

    private String type;
    private int limit;
    private int page;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "{" +
                "type:'" + type + '\'' +
                ", limit:" + limit +
                ", page:" + page +
                '}';
    }
}
