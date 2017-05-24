package com.momo.imgrecognition.module.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.utils.ShowUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


// TODO: 2017/5/16 添加历史输入标签
public class ImageDetailActivity extends BaseActivity {

    @BindView(R.id.tfl_labels)
    TagFlowLayout tflLabels;
    @BindView(R.id.tfl_history_labels)
    TagFlowLayout tflHistoryLabels;
    @BindView(R.id.fltbtn_confirm)
    FloatingActionButton fabConfirm;
    @BindView(R.id.et_customed_tag)
    EditText etCustomedTag;


    @BindView(R.id.ll_customed_tag)
    LinearLayout llCustomedTag;
    @BindView(R.id.tv_add_customed_tag)
    TextView tvAddCustomedTag;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    RelativeLayout mRoot;
    List<String> tagList;
    TagAdapter tagAdapter;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        llCustomedTag.setVisibility(View.INVISIBLE);

        mRoot = (RelativeLayout) findViewById(R.id.root);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mRoot.getWindowVisibleDisplayFrame(r);
                //r.top 是状态栏高度
                int screenHeight = mRoot.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom;

                if (softHeight > 100) {//当输入法高度大于100判定为输入法打开了
                    llCustomedTag.setTranslationY(-softHeight);
                    fadeOut(fabConfirm);
//                    fadeIn(llCustomedTag);
                    llCustomedTag.setVisibility(View.VISIBLE);
                    llCustomedTag.setTranslationY(-softHeight);
                    fadeIn(llCustomedTag);
//                    tranlateUp(-softHeight);


                } else {//否则判断为输入法隐藏了
                    llCustomedTag.setVisibility(View.GONE);
                    llCustomedTag.setTranslationY(softHeight);
                    fadeIn(fabConfirm);
                }
            }
        });

        final String[] labels = new String[]{"android", "java", "php", "hello", "world", "lalala", "lalala", "自定义"};
        tagList = new ArrayList<>(Arrays.asList(labels));
        final String[] labels2 = new String[]{"android", "java", "php", "hello", "world", "hhh"};

        tagAdapter = new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this)
                        .inflate(R.layout.layout_labels, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        };

//

        tflLabels.setAdapter(tagAdapter);

        tvAddCustomedTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

//                WindowManager.LayoutParams lp= getWindow().getAttributes();
//                lp.alpha =  0.6f;
//                getWindow().setAttributes(lp);
//                llCustomedTag.getBackground().setAlpha(255);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llCustomedTag.setVisibility(View.INVISIBLE);
                String tag = etCustomedTag.getText().toString();
                tagList.add(tag);
                tagAdapter.notifyDataChanged();

                InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                etCustomedTag.setText("");
            }
        });

        fabConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowUtil.toast("提交！");
            }
        });

        tflHistoryLabels.setAdapter(new TagAdapter<String>(labels2) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this)
                        .inflate(R.layout.layout_normal_label, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        });

        tflHistoryLabels.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String tag = labels[position];
                tagList.add(tag);
                tagAdapter.notifyDataChanged();
//                selectLastOne();
                return true;
            }
        });
    }

    private void selectLastOne() {
        int lastIndex = tagAdapter.getCount() - 1;
        TagView tagView = (TagView) tflLabels.getChildAt(lastIndex);
        TextView tvLast = (TextView) tagView.getTagView();
        tagView.removeAllViews();
        tagView.addView(tvLast);
        tvLast.setSelected(true);
        tflLabels.removeView(tflHistoryLabels.getChildAt(lastIndex));
        tflLabels.addView(tagView);
    }

    @OnClick(R.id.iv_image)
    public void onViewClicked() {
        Intent intent = new Intent(this, PreviewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int height = getStatusBarHeight();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        params.topMargin = height;
        toolbar.setLayoutParams(params);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void fadeIn(View view){
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        animatorSet.setDuration(300);
        animatorSet.play(scaleX).with(scaleY).with(fadeIn);
        animatorSet.start();
    }

    public void fadeOut(View view){
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1, 0);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        animatorSet.setDuration(300);
        animatorSet.play(scaleX).with(scaleY).with(fadeOut);
        animatorSet.start();
    }

    private void tranlateUp(int height){
        ObjectAnimator tranlateUp = ObjectAnimator.ofFloat(llCustomedTag, "translationY", 0, height);
        tranlateUp.setDuration(350);
        tranlateUp.start();
    }




}
