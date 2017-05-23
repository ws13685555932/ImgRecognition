package com.momo.imgrecognition.module.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.cb_not_rcv_msg)
    CheckBox cbNotRcvMsg;
    @BindView(R.id.ll_not_rcv_msg)
    LinearLayout llNotRcvMsg;
    @BindView(R.id.cb_close_anim)
    CheckBox cbCloseAnim;
    @BindView(R.id.rl_close_anim)
    RelativeLayout rlCloseAnim;
    @BindView(R.id.ll_close_anim)
    LinearLayout llCloseAnim;
    @BindView(R.id.ll_only_wifi_down)
    LinearLayout llOnlyWifiDown;
    @BindView(R.id.ll_my_download)
    LinearLayout llMyDownload;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;
    @BindView(R.id.ll_feedback)
    LinearLayout llFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_close_anim, R.id.ll_only_wifi_down,
            R.id.ll_my_download, R.id.ll_clear_cache,
            R.id.ll_check_update, R.id.ll_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_close_anim:
                break;
            case R.id.ll_only_wifi_down:
                break;
            case R.id.ll_my_download:
                break;
            case R.id.ll_clear_cache:
                break;
            case R.id.ll_check_update:
                break;
            case R.id.ll_feedback:
                break;
        }
    }
}
