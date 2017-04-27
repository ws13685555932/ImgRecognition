package com.momo.imgrecognition.module.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.login.presenter.LoginPresenter;
import com.momo.imgrecognition.module.login.view.ClearEditText;
import com.momo.imgrecognition.module.login.view.ILoginView;
import com.momo.imgrecognition.module.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ILoginView{

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
                mLoginPresenter.login();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ThemeUtils.refreshUI(this);
    }
}
