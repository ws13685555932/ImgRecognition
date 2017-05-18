package com.momo.imgrecognition.module.detail;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


// TODO: 2017/5/16 添加历史输入标签
public class ImageDetailActivity extends AppCompatActivity {

    @BindView(R.id.tfl_labels)
    TagFlowLayout tflLabels;
    @BindView(R.id.tfl_history_labels)
    TagFlowLayout tflHistoryLabels;
    @BindView(R.id.fltbtn_confirm)
    FloatingActionButton fltbtnConfirm;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

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
                    fltbtnConfirm.setVisibility(View.INVISIBLE);
                } else {//否则判断为输入法隐藏了
                    fltbtnConfirm.setVisibility(View.VISIBLE);
                }
            }
        });

        final String[] labels = new String[]{"android", "java", "php", "hello", "world", "lalala", "lalala", "自定义"};
        tagList = new ArrayList<>(Arrays.asList(labels));
        final String[] labels2 = new String[]{"android", "java", "php", "hello", "world", "hhh"};

        tagAdapter = new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this).inflate(R.layout.layout_labels, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        };

        LayoutTransition transition = new LayoutTransition();
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(null, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(null, "scaleY", 0, 1f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(null, "alpha", 0f,1f);
        animatorSet.setDuration(500);
        animatorSet.play(scaleX).with(scaleY).with(fadeIn);//两个动画同时开始
        transition.setAnimator(LayoutTransition.APPEARING, animatorSet);

//        //使用滑动动画代替默认布局改变的动画
//        //这个动画会让视图滑动进入并短暂地缩小一半，具有平滑和缩放的效果
//        PropertyValuesHolder pvhFade = PropertyValuesHolder.ofFloat("alpha", 1, 0.1f, 1);
//        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1, 0.5f, 1f);
//        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1,0.5f, 1f);
//
//        //这里将上面三个动画综合
//        Animator changingDisappearAnim = ObjectAnimator.ofPropertyValuesHolder(
//                tflLabels, pvhFade, pvhScaleY, pvhScaleX);
//        changingDisappearAnim.setDuration(500);
//        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changingDisappearAnim);
//        transition.setStagger(LayoutTransition.CHANGE_APPEARING,50);
//        transition.setStagger(LayoutTransition.APPEARING,50);

        tflLabels.setLayoutTransition(transition);

        tflLabels.setAdapter(tagAdapter);

        tvAddCustomedTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llCustomedTag.setVisibility(View.VISIBLE);
                InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
//                TagView tagView = new TagView(ImageDetailActivity.this);
//                TextView tv = (TextView) tagView.getTagView();
//                tv.setText(tag);
                tagList.add(tag);
                tagAdapter.notifyDataChanged();
                InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                etCustomedTag.setText("");

//                WindowManager.LayoutParams lp= getWindow().getAttributes();
//                lp.alpha = 1;
//                getWindow().setAttributes(lp);
            }
        });

        tflHistoryLabels.setAdapter(new TagAdapter<String>(labels2) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this).inflate(R.layout.layout_labels, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        });
    }

}
