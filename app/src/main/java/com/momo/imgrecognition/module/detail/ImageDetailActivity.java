package com.momo.imgrecognition.module.detail;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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

    RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        mRoot = (RelativeLayout) findViewById(R.id.root);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mRoot.getWindowVisibleDisplayFrame(r);
                //r.top 是状态栏高度
                int screenHeight = mRoot.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom ;
                if (softHeight>100){//当输入法高度大于100判定为输入法打开了
                    fltbtnConfirm.setVisibility(View.INVISIBLE);
                }else {//否则判断为输入法隐藏了
                    fltbtnConfirm.setVisibility(View.VISIBLE);
                }
            }
        });

        final String[] labels = new String[]{"android", "java", "php", "hello", "world", "lalala","lalala","lalala"};
        final String[] labels2 = new String[]{"android", "java", "php", "hello", "world", "hhh"};

        tflLabels.setAdapter(new TagAdapter<String>(labels) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this).inflate(R.layout.layout_labels, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        });

        tflLabels.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position == labels.length - 1) {

                }
                return true;
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

        etCustomedTag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                fltbtnConfirm.setVisibility(View.GONE);
            }
        });
    }
}
