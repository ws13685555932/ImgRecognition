package com.momo.imgrecognition.module.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.customedview.LoadDialog;
import com.momo.imgrecognition.module.login.presenter.LoginPresenter;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.module.register.VarifyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity implements ILoginView {

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
        mLoadDialog = new LoadDialog(this);
        mLoadDialog.show();
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
