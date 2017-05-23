package com.momo.imgrecognition.module.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.changeskin.ChangeSkinActivity;
import com.momo.imgrecognition.module.history.HistoryActivity;
import com.momo.imgrecognition.module.history.HistoryBean;
import com.momo.imgrecognition.module.main.adapter.ViewPagerAdapter;
import com.momo.imgrecognition.module.main.view.CategoryFragment;
import com.momo.imgrecognition.module.main.view.RecommendFragment;
import com.momo.imgrecognition.module.myinfo.MyInfoActivity;
import com.momo.imgrecognition.module.mymessage.MyMessageActivity;
import com.momo.imgrecognition.module.myscore.MyScoreActivity;
import com.momo.imgrecognition.module.mytask.MyTaskActivity;
import com.momo.imgrecognition.module.settings.SettingsActivity;
import com.momo.imgrecognition.module.taglater.TagLaterActivity;
import com.momo.imgrecognition.utils.ActivityManager;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.theme_color_primary));
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();


        List<Fragment> fragments = new ArrayList<>();
        RecommendFragment recommendFragment = new RecommendFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        fragments.add(recommendFragment);
        fragments.add(categoryFragment);
        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.getTabAt(0).setText("推荐");
        tabLayout.getTabAt(1).setText("分类");


        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
            }
        });
        navView.setCheckedItem(R.id.nav_home);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        drawer.closeDrawer(navView);
                        break;
                    case R.id.nav_change_skin:
                        changeSkin();
                        break;
                    case R.id.nav_my_message:
                        toMyMessage();
                        break;
                    case R.id.nav_my_score:
                        toMyScore();
                        break;
                    case R.id.nav_history:
                        toHistory();
                        break;
                    case R.id.nav_my_task:
                        toMyTask();
                        break;
                    case R.id.nav_settings:
                        toSettings();
                        break;
                    case R.id.nav_tag_later:
                        toTaglatter();
                        break;
                }
                return true;
            }
        });

        View header = navView.getHeaderView(0);
        CircleImageView ivUserIconNav = (CircleImageView) header.findViewById(R.id.civ_user_icon);
        ivUserIconNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);

            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        dynamicAddView(toolbar,"background",R.color.colorPrimary);

    }

    private void toTaglatter() {
        Intent intent = new Intent(this, TagLaterActivity.class);
        startActivity(intent);
    }

    private void toSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void toMyTask() {
        Intent intent = new Intent(this, MyTaskActivity.class);
        startActivity(intent);
    }

    private void toHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void toMyScore() {
        Intent intent = new Intent(this, MyScoreActivity.class);
        startActivity(intent);
    }

    private void initData() {
        String username = (String) SharedUtil.getParam(UserConfig.USER_NAME, "");
        tvUserName.setText(username);

    }

    private void toMyMessage() {
        Intent intent = new Intent(this, MyMessageActivity.class);
        startActivity(intent);
    }

    private void changeSkin() {
        Intent intent = new Intent(MainActivity.this, ChangeSkinActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_user_icon)
    public void onDrawerOpen() {
        drawer.openDrawer(navView);
    }

    @Override
    public void finish() {
        exitBy2Click();
    }

    private boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            ShowUtil.toast("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ActivityManager.finishAll();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        drawer.closeDrawer(navView);
    }

}
