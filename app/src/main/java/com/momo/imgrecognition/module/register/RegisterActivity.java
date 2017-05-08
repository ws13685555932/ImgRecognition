package com.momo.imgrecognition.module.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.module.login.presenter.LoginPresenter;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class RegisterActivity extends AppCompatActivity implements IRegisterView{

    String phoneNumber;
    @BindView(R.id.et_username)
    ClearEditText etUsername;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.tv_warning)
    TextView tvWarning;
    @BindView(R.id.btn_register)
    Button btnRegister;

    RegisterPresenter mRegisterPresenter = new RegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        getData();
    }

    private void getData() {
        phoneNumber = getIntent().getStringExtra("phone_number");
    }


    @OnClick(R.id.btn_register)
    public void onRegister() {
//        mRegisterPresenter.register();
        String username  = getUsername();
        String password = getPassword();
        String phoneNumber = getPhoneNumber();

        RegisterBean bean = new RegisterBean(password,username);
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        Observable<ResponseInfo<RegisterResponse>> call = userService.register(bean);
        call.compose(RxSchedulersHelper.<ResponseInfo<RegisterResponse>>io_main())
                .subscribe(new HttpObserver<RegisterResponse>() {


                    @Override
                    public void onSuccess(RegisterResponse registerResponse) {
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String message) {
                        Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void showError(String message) {
        tvWarning.setText(message);
    }
}
