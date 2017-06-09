package com.momo.imgrecognition.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.category.CategoryActivity;
import com.momo.imgrecognition.module.main.bean.CategoryBean;
import com.momo.imgrecognition.module.main.view.CategoryFragment;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public class CategoryAdapter extends CommonAdapter<CategoryBean> {
    private Context mContext;

    public CategoryAdapter(Context mContext, List<CategoryBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,
                convertView,parent,R.layout.item_grid_category,position);
        TextView tvCate = holder.getView(R.id.tv_category);
        ImageView ivIcon = holder.getView(R.id.iv_icon);

        RelativeLayout rlRoot = holder.getView(R.id.rl_category);
        rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CategoryActivity.class);
                intent.putExtra("category", mDatas.get(position).getCategory());
                mContext.startActivity(intent);
            }
        });
        CategoryBean bean = (CategoryBean) getItem(position);
        tvCate.setText(bean.getCategory());
        ivIcon.setImageDrawable(mContext.getResources().getDrawable(bean.getResId()));
        return holder.getConvertView();
    }
}
