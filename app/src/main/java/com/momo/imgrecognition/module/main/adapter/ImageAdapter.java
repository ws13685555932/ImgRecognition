package com.momo.imgrecognition.module.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;
import com.momo.imgrecognition.module.main.bean.ImageBean;
import com.momo.imgrecognition.module.main.view.PopupWindowWithAnim;

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
        ImageView iv_more;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textView = (TextView) itemView.findViewById(R.id.tv_image);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            iv_more = (ImageView) itemView.findViewById(R.id.iv_more);
        }
    }

    public ImageAdapter(List<ImageBean> list, Context mContext) {
        this.imageList = list;
        this.mContext = mContext;
    }


    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_recommend, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder holder, int position) {
        ImageBean bean = imageList.get(position);
        holder.textView.setText(bean.getName());
        Glide.with(mContext).load(bean.getImgId()).into(holder.imageView);
        holder.iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpWindow(holder.iv_more);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    private void showPopUpWindow(View view) {
        View popView = LayoutInflater.from(mContext).inflate(R.layout.layout_pop, null);
        PopupWindowWithAnim pop;
        pop = new PopupWindowWithAnim(mContext,popView);
        pop.setTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());

        //popupWindows中按钮设置点击事件
        TextView tv_tag_later = (TextView) popView.findViewById(R.id.tv_tag_later);
        TextView tv_not_interested = (TextView) popView.findViewById(R.id.tv_not_interested);

        pop.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = pop.getContentView().getMeasuredHeight();
        int width = pop.getContentView().getMeasuredWidth();

        int marginRight = (int) (0.05 * view.getWidth());
        int offsetX = view.getWidth() - width;
        int offsetY = view.getHeight();

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        pop.showAsDropDown(view,offsetX,offsetY);
//        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
//        pop.showAtLocation(rootView, Gravity.NO_GRAVITY, location[0] + offsetX, location[1] - height);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
