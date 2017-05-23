package com.momo.imgrecognition.module.mytask;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.main.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTaskActivity extends BaseActivity {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.theme_color_primary));
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_my_task);
        ButterKnife.bind(this);

        List<Fragment> fragments = new ArrayList<>();
        TaskFragment taskFragment = new TaskFragment();
        AchievementFragment achievementFragment = new AchievementFragment();
        fragments.add(taskFragment);
        fragments.add(achievementFragment);
        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("我的任务");
        tabLayout.getTabAt(1).setText("我的成就");
    }
}
