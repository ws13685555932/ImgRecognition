package com.momo.imgrecognition.module.history;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.customedview.InfoDialog;
import com.momo.imgrecognition.customedview.PopupWindowWithAnim;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;
import com.momo.imgrecognition.utils.ShowUtil;
import com.momo.imgrecognition.utils.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class HistoryAdapter extends CommonAdapter<HistoryBean> implements View.OnClickListener {
    private Context mContext;

    public HistoryAdapter(Context mContext, List<HistoryBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent
                , R.layout.item_list_history, position);

        ImageView ivImage = holder.getView(R.id.iv_image);
        TextView tvTags = holder.getView(R.id.tv_tags);
        TextView tvAdminName = holder.getView(R.id.tv_admin_name);
        TextView tvTime = holder.getView(R.id.tv_time);
        ImageButton ibMore = holder.getView(R.id.ib_more);
        LinearLayout llHistory = holder.getView(R.id.ll_history);

        final HistoryBean bean = (HistoryBean) getItem(position);
        Glide.with(mContext).load(bean.getPicUrl()).into(ivImage);
//        ivImage.setBackgroundResource(bean.getResId());

        SpannableString spanText = new SpannableString(bean.getTagStr());
        spanText.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.theme_color_primary))
                ,0, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvTags.setText(spanText);
        tvAdminName.setText(bean.getAdminName());
        tvTime.setText(bean.getTime());

        ibMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition = position;
                showDeleteDialog(view);
            }
        });

        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("tags",bean.getTags());
                intent.putExtra("imageId",bean.getPicId());
                mContext.startActivity(intent);
            }
        });
        return holder.getConvertView();
    }

    int mPosition;
    PopupWindow pop;
    private void showDeleteDialog(View view) {
        View popView = LayoutInflater.from(mContext).inflate(R.layout.dialog_delete, null);

        pop = new PopupWindowWithAnim(mContext, popView);
        pop.setTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable());

        //popupWindows中按钮设置点击事件
        LinearLayout ll_delete = (LinearLayout) popView.findViewById(R.id.ll_delete);

        ll_delete.setOnClickListener(this);

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

        ShowUtil.print("y:" + location[1]);

        int border = screenY * 2 / 3;
        ShowUtil.print("y:" + location[1]);
        ShowUtil.print("screenY:" + screenY);
        ShowUtil.print("border:" + border);
        if (location[1] <= border) {
            offsetY = view.getHeight();
            pop.showAsDropDown(view, offsetX, offsetY);
        } else {
            offsetY = view.getHeight() * 2 + height;
            pop.showAsDropDown(view, offsetX, -offsetY);
        }

//        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
//        pop.showAtLocation(rootView, Gravity.NO_GRAVITY, location[0] + offsetX, location[1] - height);

    }

    @Override
    public void onClick(View view) {
        mDatas.remove(mPosition);
        pop.dismiss();
        notifyDataSetChanged();
    }
}
