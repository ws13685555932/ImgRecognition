package com.momo.imgrecognition.module.detail;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewActivity extends AppCompatActivity {

    @BindView(R.id.photo_view)
    ImageView photoView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        ButterKnife.bind(this);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        int url = getIntent().getIntExtra("url",0);
        Glide.with(this).load(url).into(photoView);

//        // 启用图片缩放功能
//        photoView.enable();
//        // 获取图片信息
//        Info info = photoView.getInfo();
//        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
//        photoView.animaFrom(info);
//        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
//        photoView.animaTo(info, new Runnable() {
//            @Override
//            public void run() {
//                //动画完成监听
//            }
//        });
//        // 获取/设置 动画持续时间
//        photoView.setAnimaDuring(200);
//        // 获取/设置 最大缩放倍数
//        photoView.setMaxScale(3);
//        float maxScale = photoView.getMaxScale();
//        // 设置动画的插入器
//        photoView.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
