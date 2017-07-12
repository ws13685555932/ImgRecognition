package com.momo.imgrecognition.module.detail;

/**
 * @author wangsheng
 * @date 2017-06-25.
 */

public class TagString {
    String tag;
    int type;

    public TagString(String tag, int type) {
        this.tag = tag;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
