package com.momo.imgrecognition.module.taglater.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
//TODO:Adapter未完成
public class TagListAdapter extends CommonAdapter<TagLaterBean> {
    private Context mContext;

    public TagListAdapter(Context mContext, List<TagLaterBean> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertView,parent
                , R.layout.item_list_tag_later,position);
        ImageView ivImage = holder.getView(R.id.iv_image);
        TagFlowLayout tflTagList = holder.getView(R.id.tfl_tags);
        TextView tvAdminName = holder.getView(R.id.tv_admin_name);
        ImageButton ibMore = holder.getView(R.id.ib_more);

        TagLaterBean tagLater = (TagLaterBean) getItem(position);
        ivImage.setBackgroundResource(tagLater.getResId());
        String tagStr = tagLater.getTagStr();
        ArrayList<String> tagList = new ArrayList<>(Arrays.asList(tagStr.split(","))) ;
        tflTagList.setAdapter(new TagAdapter<String>(tagList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(mContext)
                        .inflate(R.layout.layout_tags, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        });
        tvAdminName.setText(tagLater.getAdminName());

        return holder.getConvertView();
    }
}
