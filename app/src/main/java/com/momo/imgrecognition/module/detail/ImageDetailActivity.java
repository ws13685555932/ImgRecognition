package com.momo.imgrecognition.module.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailActivity extends AppCompatActivity {

    @BindView(R.id.tfl_labels)
    TagFlowLayout tflLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        final String[] labels = new String[]{"android", "java", "php", "hello", "world","自定义"};

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
                if(position == labels.length-1) {

                }
                return true;
            }
        });
    }
}
