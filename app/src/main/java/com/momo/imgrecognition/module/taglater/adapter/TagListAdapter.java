package com.momo.imgrecognition.module.taglater.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;
import com.momo.imgrecognition.utils.CommonAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
//TODO:Adapter未完成
public class TagListAdapter extends CommonAdapter<TagLaterBean> {

    public TagListAdapter(Context mContext, List<TagLaterBean> mDatas) {
        super(mContext, mDatas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
