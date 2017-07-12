package com.momo.imgrecognition.module.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.changeskin.ChangeSkinActivity;
import com.momo.imgrecognition.module.gallery.GalleryActivity;
import com.momo.imgrecognition.module.history.HistoryActivity;
import com.momo.imgrecognition.module.main.adapter.ViewPagerAdapter;
import com.momo.imgrecognition.module.main.view.CategoryFragment;
import com.momo.imgrecognition.module.main.view.RecommendFragment;
import com.momo.imgrecognition.module.myinfo.MyInfoActivity;
import com.momo.imgrecognition.module.mymessage.MyMessageActivity;
import com.momo.imgrecognition.module.myscore.MyScoreActivity;
import com.momo.imgrecognition.module.mytask.MyTaskActivity;
import com.momo.imgrecognition.module.search.SearchActivity;
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

import static android.R.attr.level;
import static android.R.attr.name;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_MY_INFO = 1001;
    private static final int REQUEST_MY_MESSAGE = 1002;
    private static final int REQUEST_MY_SCORE = 1003;
    private static final int REQUEST_TAG_LATER = 1004;
    private static final int REQUEST_HISTORY = 1005;
    private static final int REQUEST_MY_TASK = 1006;
    private static final int REQUEST_CHANGE_SKIN = 1007;
    private static final int REQUEST_SETTINGS = 1008;


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
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_download)
    ImageView ivDownload;

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
//                    case R.id.nav_tag_later:
//                        toTaglatter();
//                        break;
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
                startActivityForResult(intent, REQUEST_MY_INFO);
            }
        });
    }

    // TODO: 2017/5/24 等级
    private void initData() {
        //自动登录
        if (getIntent() != null) {
            int level = getIntent().getIntExtra("level", 0);
            String iconUrl = getIntent().getStringExtra("iconUrl");
            String name = getIntent().getStringExtra("name");

            tvUserName.setText(name);
            View header = navView.getHeaderView(0);
            CircleImageView ivUserIconNav = (CircleImageView) header.findViewById(R.id.civ_user_icon);
            if (iconUrl != null) {
                Glide.with(MainActivity.this).load(iconUrl).into(ivUserIcon);
                Glide.with(MainActivity.this).load(iconUrl).into(ivUserIconNav);
            }
            TextView tvNavUsername = (TextView) header.findViewById(R.id.tv_user_name);
            tvNavUsername.setText(name);
            TextView tvNavLevel = (TextView) header.findViewById(R.id.tv_level);
            tvNavLevel.setText("Lv." + level);


        }
//            String type = getIntent().getStringExtra("type");
//            if (type != null) {
//                LoginBiz loginBiz = new LoginBiz();
//                String username = (String) SharedUtil.getParam(UserConfig.USER_NAME, "");
//                String password = (String) SharedUtil.getParam(UserConfig.USER_PASSWORD, "");
//                User user = new User(password, username);
//                loginBiz.login(user, new OnLoginListener() {
//                    @Override
//                    public void loginSuccess(LoginResponse response) {
//                        tvUserName.setText(response.getName());
//                        Glide.with(MainActivity.this).load(response.getAvatarUrl()).into(ivUserIcon);
//                        View header = navView.getHeaderView(0);
//                        CircleImageView ivUserIconNav = (CircleImageView) header.findViewById(R.id.civ_user_icon);
//                        Glide.with(MainActivity.this).load(response.getAvatarUrl()).into(ivUserIconNav);
//                    }
//
//                    @Override
//                    public void loginFailed(String msg) {
//
//                    }
//                });
//                return;
//            }
//        }

//        //正常登录
//        String username = (String) SharedUtil.getParam(UserConfig.USER_NAME, "");
//        tvUserName.setText(username);
//        String userIconUrl = (String) SharedUtil.getParam(UserConfig.USER_ICON_URL, "");
//        if (!userIconUrl.equals("")) {
//            Glide.with(this).load(userIconUrl).into(ivUserIcon);
//            View header = navView.getHeaderView(0);
//            CircleImageView ivUserIconNav = (CircleImageView) header.findViewById(R.id.civ_user_icon);
//            Glide.with(this).load(userIconUrl).into(ivUserIconNav);
//            BitmapUtil.downloadUserIcon(userIconUrl);
//        }


    }


    private void toTaglatter() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, TagLaterActivity.class);
        startActivityForResult(intent, REQUEST_TAG_LATER);
    }

    private void toSettings() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, REQUEST_SETTINGS);
    }

    private void toMyTask() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, MyTaskActivity.class);
        startActivityForResult(intent, REQUEST_MY_TASK);
    }

    private void toHistory() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivityForResult(intent, REQUEST_HISTORY);
    }

    private void toMyScore() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, MyScoreActivity.class);
        startActivityForResult(intent, REQUEST_MY_SCORE);
    }

    private void toMyMessage() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(this, MyMessageActivity.class);
        startActivityForResult(intent, REQUEST_MY_MESSAGE);
    }

    private void changeSkin() {
        drawer.closeDrawer(navView);
        Intent intent = new Intent(MainActivity.this, ChangeSkinActivity.class);
        startActivityForResult(intent, REQUEST_CHANGE_SKIN);
    }


    @Override
    public void onBackPressed() {
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
            Process.killProcess(Process.myPid());
        }
    }

    // TODO: 2017/5/24 更新名字头像和等级 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MY_INFO) {

            ShowUtil.print("user icon changed ");
            String userIconUrl = (String) SharedUtil.getParam(UserConfig.USER_ICON_URL, "");
            Glide.with(this).load(userIconUrl).into(ivUserIcon);
            View header = navView.getHeaderView(0);
            CircleImageView ivUserIconNav = (CircleImageView) header.findViewById(R.id.civ_user_icon);
            Glide.with(this).load(userIconUrl).into(ivUserIconNav);

            tvUserName.setText((String) SharedUtil.getParam(UserConfig.USER_NAME, ""));
            TextView tvNavUsername = (TextView) header.findViewById(R.id.tv_user_name);
            tvNavUsername.setText((String) SharedUtil.getParam(UserConfig.USER_NAME, ""));
        }

    }

    @OnClick({R.id.iv_user_icon, R.id.iv_search, R.id.iv_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                drawer.openDrawer(navView);
                break;
            case R.id.iv_search:
                toSearchActivity();
                break;
            case R.id.iv_download:
                toDownloadActivity();
                break;
        }
    }

    private void toDownloadActivity() {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    private void toSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
