package com.momo.imgrecognition.module.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kevin.crop.UCrop;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.Config;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.ButterKnife;

public class ChangeInfoActivity extends AppCompatActivity {
    private String type;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);

        type = getIntent().getStringExtra("type");
        initFragment(type);

    }

    public void initFragment(String type) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);

        //todo:判断type为空
        if (type.equals(Config.TYPE_NAME) || type.equals(Config.TYPE_EMAIL)) {
            initNameFragment(bundle);
        } else {
            initDescrptionFragment();
        }
        mTransaction.commit();
    }

    private void initDescrptionFragment() {
        ChangeDescriptionFragment descriptionFragment = new ChangeDescriptionFragment();
        descriptionFragment.setToolbarListener(new ChangeNameFragment.ToolbarListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onSave(String text) {
                setResult(RESULT_OK, new Intent()
                        .putExtra("text", text));
                ShowUtil.print(text);
                finish();
            }
        });
        mTransaction.replace(R.id.container, descriptionFragment);
    }

    private void initNameFragment(Bundle bundle) {
        ChangeNameFragment nameFragment = new ChangeNameFragment();
        nameFragment.setArguments(bundle);
        nameFragment.setToolbarListener(new ChangeNameFragment.ToolbarListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onSave(String text) {
                setResult(RESULT_OK, new Intent()
                        .putExtra("text", text));
                ShowUtil.print(text);
                finish();
            }
        });
        mTransaction.replace(R.id.container, nameFragment);
    }


}
