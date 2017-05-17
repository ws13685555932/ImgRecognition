package com.momo.imgrecognition.module.mytask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.customedview.ProgressView;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class TaskAdapter extends CommonAdapter<TaskBean> {
    private Context mContext;

    public TaskAdapter(Context mContext, List<TaskBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertView,parent
                , R.layout.item_list_task,position);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvDes = holder.getView(R.id.tv_des);
        ProgressView progressView = holder.getView(R.id.progress);

        TaskBean task = (TaskBean) getItem(position);
        tvTitle.setText(task.getTitle());
        tvDes.setText(task.getDes());
        progressView.setTotal(task.getTotal());
        progressView.setProgress(task.getProgress());
        return holder.getConvertView();
    }
}










