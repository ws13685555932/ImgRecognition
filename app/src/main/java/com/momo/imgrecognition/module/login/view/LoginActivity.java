package com.momo.imgrecognition.module.login.view;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.module.main.view.CardPickerDialog;
import com.momo.imgrecognition.utils.ThemeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_username)
    ClearEditText etUsername;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register_account)
    TextView tvRegisterAccount;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(ThemeUtils.getColorById(this, R.color.theme_color_primary));

//        RxTextView.textChanges(etUsername)
//                .subscribe(new Consumer<CharSequence>() {
//                    @Override
//                    public void accept(@NonNull CharSequence charSequence) throws Exception {
//                        String password = etPassword.getText().toString();
//                        if(charSequence.toString().equals("") || password.equals("")){
//                            btnLogin.setEnabled(false);
//                        }else{
//                            btnLogin.setEnabled(true);
//                        }
//                    }
//                });
//
//        RxTextView.textChanges(etPassword)
//                .subscribe(new Consumer<CharSequence>() {
//                    @Override
//                    public void accept(@NonNull CharSequence charSequence) throws Exception {
//                        String username = etUsername.getText().toString();
//                        if(charSequence.toString().equals("") || username.equals("")){
//                            btnLogin.setEnabled(false);
//                        }else{
//                            btnLogin.setEnabled(true);
//                        }
//                    }
//                });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                CardPickerDialog dialog = new CardPickerDialog();
                dialog.setClickListener(new CardPickerDialog.ClickListener() {
                    @Override
                    public void onConfirm(int currentTheme) {
                        if (ThemeHelper.getTheme(LoginActivity.this) != currentTheme) {
                            ThemeHelper.setTheme(LoginActivity.this, currentTheme);
                            ThemeUtils.refreshUI(LoginActivity.this, new ThemeUtils.ExtraRefreshable() {
                                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                        @Override
                                        public void refreshGlobal(Activity activity) {
                                            //for global setting, just do once
                                            final LoginActivity context = LoginActivity.this;
                                            ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                            setTaskDescription(taskDescription);
                                            getWindow().setStatusBarColor(ThemeUtils.getColorById(context, R.color.theme_color_primary_dark));
                                        }

                                        @Override
                                        public void refreshSpecificView(View view) {
                                            //TODO: will do this for each traversal
                                            if(view instanceof ClearEditText){
                                                ((ClearEditText)view).refreshUi();
                                            }
                                        }
                                    }
                            );
                        }
                    }
                });

                dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);

            }
        });
    }
}
