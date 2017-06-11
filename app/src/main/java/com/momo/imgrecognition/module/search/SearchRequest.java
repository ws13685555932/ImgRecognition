package com.momo.imgrecognition.module.search;

/**
 * @author wangsheng
 * @date 2017-06-11.
 */

public class SearchRequest {

    /**
     * search : 小
     * limit : 20
     * page : 1
     */

    private String search;
    private int limit;
    private int page;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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
