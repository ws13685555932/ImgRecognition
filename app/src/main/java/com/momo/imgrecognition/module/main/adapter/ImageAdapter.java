package com.momo.imgrecognition.module.main.adapter;

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
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;
import com.momo.imgrecognition.module.main.bean.ImageBean;
import com.momo.imgrecognition.customedview.PopupWindowWithAnim;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ImageBean> imageList;
    int currIndex ;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView tvTagNumber;
        TextView tvRecomTags;
        TextView tvTime;
        ImageView iv_more;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            tvTagNumber = (TextView) itemView.findViewById(R.id.tv_tag_number);
            tvRecomTags = (TextView) itemView.findViewById(R.id.tv_recom_tags);
            imageView = (ImageView) itemView.findViewById(R.id.iv_image);
            iv_more = (ImageView) itemView.findViewById(R.id.ib_more);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
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
    public void onBindViewHolder(final ImageAdapter.ViewHolder holder, final int position) {
        ImageBean bean = imageList.get(position);
        holder.tvRecomTags.setText(bean.getTags());
        Glide.with(mContext).load(bean.getImgUrl()).into(holder.imageView);
        holder.iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currIndex = position;
                showPopUpWindow(holder.iv_more);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ShowUtil.print("id: main adapter " + SharedUtil.getParam(UserConfig.USER_ID,""));
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("imageId" , imageList.get(position).getImageId());
                mContext.startActivity(intent);
            }
        });
        holder.tvTagNumber.setText("已有" + bean.getTagNum() + "个标签");
        holder.tvTime.setText(bean.getTime());
    }
    PopupWindowWithAnim pop;
    private void showPopUpWindow(View view) {
        View popView = LayoutInflater.from(mContext).inflate(R.layout.layout_pop, null);

        pop = new PopupWindowWithAnim(mContext,popView);
        pop.setTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());

        //popupWindows中按钮设置点击事件
//        LinearLayout ll_tag_later = (LinearLayout) popView.findViewById(R.id.ll_tag_later);
        LinearLayout ll_not_interested = (LinearLayout) popView.findViewById(R.id.ll_not_interested);

//        ll_tag_later.setOnClickListener(this);
        ll_not_interested.setOnClickListener(this);

        pop.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = pop.getContentView().getMeasuredHeight();
        int width = pop.getContentView().getMeasuredWidth();

        //get screenY
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int screenY = dm.heightPixels;

        int offsetX = view.getWidth() - width;
        int offsetY;

        //get absolute x and y of view
        int[] location = new int[2];
        view.getLocationOnScreen(location);

//        ShowUtil.print("y:" + location[1]);

        int border = screenY * 2 / 3 ;
//        ShowUtil.print("y:" + location[1]);
//        ShowUtil.print("screenY:" + screenY);
//        ShowUtil.print("border:" + border);
        if(location[1] <= border){
            offsetY = view.getHeight();
            pop.showAsDropDown(view,offsetX,offsetY);
        }else{
            offsetY = view.getHeight()*2+height;
            pop.showAsDropDown(view,offsetX,-offsetY);
        }

//        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
//        pop.showAtLocation(rootView, Gravity.NO_GRAVITY, location[0] + offsetX, location[1] - height);
    }

    private void setDrawableBounds(TextView textView) {
        Drawable left = textView.getCompoundDrawables()[0];
        left.setBounds(0, 0, 80, 80);
        textView.setCompoundDrawables(left, null, null, null);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.ll_tag_later:
//                pop.dismiss();
//                break;
            case R.id.ll_not_interested:
                pop.dismiss();
                imageList.remove(currIndex);
                notifyDataSetChanged();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
