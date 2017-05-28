package com.momo.imgrecognition.module.history;

/**
 * Created by Administrator on 2017/5/28.
 */

public class HistoryRequest {
    /**
     * id : 4
     * limit : 20
     * page : 1
     */

    private String id;
    private int limit;
    private int page;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
