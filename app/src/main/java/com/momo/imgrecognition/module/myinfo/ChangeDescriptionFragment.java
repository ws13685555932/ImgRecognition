package com.momo.imgrecognition.module.myinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/10.
 */

public class ChangeDescriptionFragment extends Fragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tli_description)
    TextInputLayout tliDescription;
    Unbinder unbinder;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.et_description)
    TextInputEditText etDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_change_description, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        return contentView;
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
                if (!etDescription.getText().toString().equals("")) {
                    mToolbarListener.onSave(etDescription.getText().toString());
                } else {
                    tliDescription.setError("个人介绍不能为空！");
                }
                break;
        }
    }


    interface ToolbarListener {
        void onBack();

        void onSave(String text);
    }

    private ChangeDescriptionFragment.ToolbarListener mToolbarListener;

    public void setToolbarListener(ChangeDescriptionFragment.ToolbarListener listener) {
        this.mToolbarListener = listener;
    }
}
