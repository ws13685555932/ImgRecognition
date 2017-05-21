package com.momo.imgrecognition.module.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.utils.CommonAdapter;
import com.momo.imgrecognition.utils.CommonViewHolder;

import java.text.FieldPosition;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21.
 */

public class SearchAdpter extends CommonAdapter<String> {
    private Context mContext;

    public SearchAdpter(Context mContext, List<String> mDatas) {
        super(mContext, mDatas);
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext,convertview,parent
                ,R.layout.item_list_search, position);

        TextView tvText = holder.getView(R.id.tv_history_text);
        tvText.setText((String) getItem(position));
        return holder.getConvertView();
    }
}
