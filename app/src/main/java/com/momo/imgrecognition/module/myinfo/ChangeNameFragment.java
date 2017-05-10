package com.momo.imgrecognition.module.myinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.Config;
import com.momo.imgrecognition.customedview.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_error)
    TextView tvError;

    private String mType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_change_name, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        Bundle bundle = getArguments();
        mType = bundle.getString("type");
        initView(mType);
        return contentView;
    }

    public void initView(String type) {
        if (type.equals(Config.TYPE_NAME)) {
            title.setText("修改昵称");
            etChangeName.setHint("请输入昵称");
        } else if (type.equals(Config.TYPE_EMAIL)) {
            title.setText("修改邮箱");
            etChangeName.setHint("请输入邮箱");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                mToolbarListener.onBack();
                break;
            case R.id.tv_save:
                if (!etChangeName.getText().toString().equals("")) {
                    mToolbarListener.onSave(etChangeName.getText().toString());
                } else {
                    tvError.setVisibility(View.VISIBLE);
                    if (mType.equals(Config.TYPE_NAME)) {
                        tvError.setText("昵称不能为空！");
                    } else if (mType.equals(Config.TYPE_EMAIL)) {
                        tvError.setText("邮箱不能为空！");
                    }
                }
                break;
        }
    }

    interface ToolbarListener {
        void onBack();

        void onSave(String text);
    }

    private ToolbarListener mToolbarListener;

    public void setToolbarListener(ToolbarListener listener) {
        this.mToolbarListener = listener;
    }

}
