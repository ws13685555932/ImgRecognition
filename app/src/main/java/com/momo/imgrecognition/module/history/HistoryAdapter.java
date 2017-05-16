package com.momo.imgrecognition.module.history;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;
import com.momo.imgrecognition.utils.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class HistoryAdapter extends CommonAdapter<HistoryBean> {
    private Context mContext;

    public HistoryAdapter(Context mContext, List<HistoryBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertView,parent
            , R.layout.item_list_history,position);

        ImageView ivImage = holder.getView(R.id.iv_image);
        TextView tvTags = holder.getView(R.id.tv_tags);
        TextView tvAdminName = holder.getView(R.id.tv_admin_name);
        TextView tvTime = holder.getView(R.id.tv_time);
        ImageButton ibMore = holder.getView(R.id.ib_more);

        HistoryBean bean = (HistoryBean) getItem(position);
        ivImage.setBackgroundResource(bean.getResId());
        tvTags.setText(bean.getTags());
        tvAdminName.setText(bean.getAdminName());
        tvTime.setText(TimeUtil.timeStamp2Date(bean.getTime()));
        return  holder.getConvertView();
    }
}
