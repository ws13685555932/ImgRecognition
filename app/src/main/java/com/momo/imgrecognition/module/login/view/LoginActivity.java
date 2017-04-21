package com.momo.imgrecognition.module.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import solid.ren.skinlibrary.base.SkinBaseActivity;

public class LoginActivity extends SkinBaseActivity {

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
