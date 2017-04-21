package com.momo.imgrecognition.module.main.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.bean.ImageBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private List<ImageBean> imageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textView = (TextView) itemView.findViewById(R.id.tv_image);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    public ImageAdapter(List<ImageBean> list,Context mContext) {
        this.imageList = list;
        this.mContext = mContext;
    }


    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        ImageBean bean = imageList.get(position);
        holder.textView.setText(bean.getName());
        Glide.with(mContext).load(bean.getImgId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
