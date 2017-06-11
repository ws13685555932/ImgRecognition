package com.momo.imgrecognition.module.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.momo.imgrecognition.MyApplication;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.login.presenter.LoginPresenter;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class RegisterActivity extends BaseActivity implements IRegisterView{

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
//        phoneNumber = "15246398801";
    }


    @OnClick(R.id.btn_register)
    public void onRegister() {

//        mRegisterPresenter.register();
        String username  = getUsername();
        final String password = getPassword();
        String phoneNumber = getPhoneNumber();

        RegisterBean bean = new RegisterBean(password,username,phoneNumber);
        ShowUtil.print(bean.toString());
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        Observable<ResponseInfo<RegisterResponse>> call = userService.register(bean);
        call.compose(RxSchedulersHelper.<ResponseInfo<RegisterResponse>>io_main())
                .subscribe(new HttpObserver<RegisterResponse>() {
                    @Override
                    public void onSuccess(RegisterResponse registerResponse) {
                        mRegisterPresenter.saveData(registerResponse,password);
                        ShowUtil.toast("注册成功！");
                        String name = registerResponse.getName();
                        String iconUrl = registerResponse.getAvatarUrl();
                        int level = registerResponse.getLevel();
                        closeSoft();
                        setId(registerResponse.getId());
                        toMainActivity(level,iconUrl,name);
                    }

                    @Override
                    public void onFailed(String message) {
                        showError(message);
                    }
                });
    }

    private void closeSoft() {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken()
                ,InputMethodManager.HIDE_NOT_ALWAYS);
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
        tvWarning.setVisibility(View.VISIBLE);
        tvWarning.setText(message);
    }

    @Override
    public void toMainActivity(int level, String iconUrl, String name) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("level", level);
        intent.putExtra("iconUrl", iconUrl);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
