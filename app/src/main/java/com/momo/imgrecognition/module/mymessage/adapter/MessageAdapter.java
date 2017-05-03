package com.momo.imgrecognition.module.mymessage.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilibili.magicasakura.widgets.TintTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.changeskin.bean.SkinItem;
import com.momo.imgrecognition.module.mymessage.MessageBean;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class MessageAdapter extends CommonAdapter<MessageBean>{
    private Context mContext;

    public MessageAdapter(Context mContext, List<MessageBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertView,parent,
                R.layout.item_list_message,position);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvDate = holder.getView(R.id.tv_date);

        MessageBean message = (MessageBean) getItem(position);

        tvTitle.setText(message.getTitle());
        tvContent.setText(message.getContent());
        tvDate.setText(message.getDate());

        return holder.getConvertView();
    }
}
