package com.momo.imgrecognition.module.category;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/5/25.
 */

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> {

    private Context mContext;
    private List<CateImageBean> imageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView tvTagCont;
        TextView tvTagAdmin;
        TextView tvTagNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_cate_image);
            tvTagCont = (TextView) itemView.findViewById(R.id.tv_tag_cont);
            tvTagAdmin = (TextView) itemView.findViewById(R.id.tv_tag_admin);
            tvTagNumber = (TextView) itemView.findViewById(R.id.tv_tag_number);
        }
    }

    public CateAdapter(List<CateImageBean> list, Context mContext) {
        this.imageList = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_cate_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CateImageBean bean = imageList.get(position);
        Glide.with(mContext).load(bean.getResUrl()).into(holder.imageView);

        holder.tvTagCont.setText(bean.getTagCont());
        holder.tvTagAdmin.setText(bean.getTagAdmin());
        holder.tvTagNumber.setText(bean.getTagNum()+"个标签");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("url", imageList.get(position).getResUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}

