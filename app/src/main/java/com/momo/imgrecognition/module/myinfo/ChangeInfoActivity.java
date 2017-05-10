package com.momo.imgrecognition.module.myinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.momo.imgrecognition.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);

        initFragment();

    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ChangeNameFragment nameFragment = new ChangeNameFragment();
        ChangeDescriptionFragment descriptionFragment = new ChangeDescriptionFragment();
        transaction.replace(R.id.container, descriptionFragment);
        transaction.commit();
    }
}
