package com.momo.imgrecognition.module.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuPresenter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.customedview.LoadDialog;
import com.momo.imgrecognition.customedview.PickSexDialog;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.login.presenter.LoginPresenter;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.module.register.VarifyActivity;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity implements ILoginView {

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

    LoginPresenter mLoginPresenter = new LoginPresenter(this);

    LoadDialog mLoadDialog;

    private boolean isBackFlag = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            String flag = getIntent().getStringExtra(UserConfig.USER_LOGIN);
            if (flag != null) {
                isBackFlag = true;
            }
        }

        //自动登录
        if (!isBackFlag) {
            String name = (String) SharedUtil.getParam(UserConfig.USER_NAME, "");
            String password = (String) SharedUtil.getParam(UserConfig.USER_PASSWORD, "");

//            ShowUtil.print(name + ":" + password);
            if (!name.equals("") && !password.equals("")) {
                mLoginPresenter.autoLogin(name, password);
            }
        }

        if (!Config.IS_TEST) {
            rxBinding();
        }
    }

    private void rxBinding() {
        RxTextView.textChanges(etPassword)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        String username = etUsername.getText().toString();
                        if (charSequence.toString().equals("") || username.equals("")) {
                            btnLogin.setEnabled(false);
                        } else {
                            btnLogin.setEnabled(true);
                        }
                    }
                });
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void toRegisterActivity() {

    }

    @Override
    public void showLoadDialog() {
        mLoadDialog = new LoadDialog();
        mLoadDialog.show(getSupportFragmentManager(), LoadDialog.TAG);
    }

    @Override
    public void dismissLoadDialog() {
        mLoadDialog.dismiss();
    }

    @OnClick(R.id.btn_login)
    public void onLogin() {
        mLoginPresenter.login();
    }

    @OnClick(R.id.tv_register_account)
    public void onRegister() {
        Intent intent = new Intent(LoginActivity.this, VarifyActivity.class);
        startActivity(intent);
    }


}
