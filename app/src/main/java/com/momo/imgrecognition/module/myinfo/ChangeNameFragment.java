package com.momo.imgrecognition.module.myinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.data.AssetPathFetcher;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.Config;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/10.
 */

public class ChangeNameFragment extends Fragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.et_change_name)
    ClearEditText etChangeName;
    Unbinder unbinder;
    private String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_change_name, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        Bundle bundle = getArguments();
        String type = bundle.getString("type");
        ShowUtil.print(type);
        setType(type);
        return contentView;
    }

    public void setType(String type) {
        if(type.equals(Config.TYPE_NAME)){
            title.setText("修改昵称");
            etChangeName.setHint("请输入昵称");
        }else if(type.equals(Config.TYPE_EMAIL)){
            title.setText("修改邮箱");
            etChangeName.setHint("请输入邮箱");
        }else if(type.equals(Config.TYPE_PHONE)){
            title.setText("修改手机号");
            etChangeName.setHint("请输入手机号");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
