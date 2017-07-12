package com.momo.imgrecognition.module.firstpage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.login.LoginActivity;
import com.momo.imgrecognition.module.login.bean.LoginResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.login.biz.LoginBiz;
import com.momo.imgrecognition.module.login.biz.OnLoginListener;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import java.util.Timer;
import java.util.TimerTask;


public class FirstPageActivity extends BaseActivity {

    private boolean isStart = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        login();

//        Handler handler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message message) {
//                if(message.what == 1) {
//                    login();
//                }
//                return true;
//            }
//        });
//
//        handler.sendEmptyMessageDelayed(1,3000);
    }

    private void login() {
        String username = (String) SharedUtil.getParam(UserConfig.USER_NAME, "");
        String password = (String) SharedUtil.getParam(UserConfig.USER_PASSWORD, "");

        LoginBiz loginBiz = new LoginBiz();

        User user = new User(password, username);

        ShowUtil.print("登录请求：" + user.toString());

        loginBiz.login(user, new OnLoginListener() {
            @Override
            public void loginSuccess(LoginResponse response) {
                int level = response.getLevel();
                String iconUrl = response.getAvatarUrl();
                String name = response.getName();
                toMainActivity(level,iconUrl,name);
            }

            @Override
            public void loginFailed(String msg) {
                ShowUtil.print("跳转到LoginActivity");
                toLoginActivity();
            }
        });
    }


    private void toMainActivity(int level, String iconUrl, String name) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("level", level);
        intent.putExtra("iconUrl", iconUrl);
        intent.putExtra("name", name);
        ShowUtil.print("LoginActivity->MainActivity:"
                + "level:" + level
                + "iconUrl" + iconUrl
                + "name" + name);
        startActivity(intent);
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
