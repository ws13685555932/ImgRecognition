package com.momo.imgrecognition.module.history;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.customedview.PopupWindowWithAnim;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;
import com.momo.imgrecognition.module.main.adapter.ImageAdapter;
import com.momo.imgrecognition.module.main.bean.ImageBean;

import java.util.List;

/**
 * @author wangsheng
 * @date 2017-06-25.
 */

public class HistoryRecyAdapter extends RecyclerView.Adapter<HistoryRecyAdapter.ViewHolder>{
    private Context mContext;
    private List<HistoryBean> imageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public HistoryRecyAdapter(List<HistoryBean> list, Context mContext) {
        this.imageList = list;
        this.mContext = mContext;
    }


    @Override
    public HistoryRecyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_history, parent, false);

        return new HistoryRecyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HistoryRecyAdapter.ViewHolder holder, final int position) {
        HistoryBean bean = imageList.get(position);
        Glide.with(mContext).load(bean.getPicUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ShowUtil.print("id: main adapter " + SharedUtil.getParam(UserConfig.USER_ID,""));
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("imageId" , imageList.get(position).getPicId());
                mContext.startActivity(intent);
            }
        });
        holder.tvTime.setText(bean.getTime());
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
