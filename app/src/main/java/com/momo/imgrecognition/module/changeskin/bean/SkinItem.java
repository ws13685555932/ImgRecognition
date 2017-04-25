package com.momo.imgrecognition.module.changeskin.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/4/25.
 */

public class SkinItem {
    int iconDrawable;
    String skinText;
    int currUseDrawable;

    public int getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public String getSkinText() {
        return skinText;
    }

    public void setSkinText(String skinText) {
        this.skinText = skinText;
    }

    public int getCurrUseDrawable() {
        return currUseDrawable;
    }

    public void setCurrUseDrawable(int currUseDrawable) {
        this.currUseDrawable = currUseDrawable;
    }
}
