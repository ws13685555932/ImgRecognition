package com.momo.imgrecognition.module.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.category.CategoryActivity;
import com.momo.imgrecognition.module.main.bean.CategoryBean;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.io.File;
import java.util.List;

/**
 * @author wangsheng
 * @date 2017-06-11.
 */

public class GalleryAdapter extends CommonAdapter<File> {
    private Context mContext;

    public GalleryAdapter(Context mContext, List<File> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,
                convertView,parent, R.layout.item_grid_gallery,position);

        ImageView ivIcon = holder.getView(R.id.iv_image);

        Glide.with(mContext)
                .load(mDatas.get(position))
                .into(ivIcon);

        return holder.getConvertView();
    }
}
