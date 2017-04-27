package com.momo.imgrecognition.module.changeskin;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.bilibili.magicasakura.widgets.TintTextView;
import com.bilibili.magicasakura.widgets.TintToolbar;
import com.momo.imgrecognition.MyApplication;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.utils.ThemeHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeSkinActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.toolbar)
    TintToolbar toolbar;
    @BindView(R.id.tv_night)
    TintTextView tvNight;
    @BindView(R.id.tv_default)
    TintTextView tvDefault;
    @BindView(R.id.tv_red)
    TintTextView tvRed;
    @BindView(R.id.tv_yellow)
    TintTextView tvYellow;
    @BindView(R.id.tv_green)
    TintTextView tvGreen;
    @BindView(R.id.tv_blue)
    TintTextView tvBlue;
    @BindView(R.id.tv_purple)
    TintTextView tvPurple;

    List<TintTextView> skinList;
    private int mCurrentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        skinList = new ArrayList<>();
        skinList.add(tvDefault);
        skinList.add(tvNight);
        skinList.add(tvBlue);
        skinList.add(tvGreen);
        skinList.add(tvYellow);
        skinList.add(tvPurple);
        skinList.add(tvRed);

        for(int i=0;i<skinList.size();i++){
            TintTextView tint = skinList.get(i);
            Drawable right = tint.getCompoundDrawables()[2];
            right.setBounds(0,0,50,50);
            tint.setCompoundDrawables(tint.getCompoundDrawables()[0],null,right,null);
        }
    }
}
