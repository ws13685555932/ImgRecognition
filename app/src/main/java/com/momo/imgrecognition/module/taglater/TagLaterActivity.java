package com.momo.imgrecognition.module.taglater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagLaterActivity extends AppCompatActivity {

    @BindView(R.id.lv_tag_later)
    ListView lvTagLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_later);
        ButterKnife.bind(this);
    }
}
