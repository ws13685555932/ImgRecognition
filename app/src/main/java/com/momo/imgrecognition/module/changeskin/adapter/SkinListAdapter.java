package com.momo.imgrecognition.module.changeskin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.bilibili.magicasakura.widgets.TintTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.changeskin.bean.SkinItem;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class SkinListAdapter extends CommonAdapter<SkinItem> {
    private Context mContext;

    public SkinListAdapter(Context mContext, List<SkinItem> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertView,parent,
                R.layout.item_list_skin,position);
        TintTextView tv_skin = holder.getView(R.id.tint_tv_skin);
        Drawable right = tv_skin.getCompoundDrawables()[2];
        if(right != null) {
            right.setBounds(0, 0, 60, 60);
            tv_skin.setCompoundDrawables(tv_skin.getCompoundDrawables()[0], null, right, null);
        }
        SkinItem item = mDatas.get(position);
        tv_skin.setText(item.getSkinText());
        return holder.getConvertView();
    }
}
