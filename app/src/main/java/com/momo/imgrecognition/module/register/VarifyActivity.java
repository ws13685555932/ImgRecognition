package com.momo.imgrecognition.module.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.customedview.ClearEditText;
import com.momo.imgrecognition.utils.ShowUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class VarifyActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_phone_number)
    ClearEditText etPhoneNumber;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.btn_next)
    Button btnNext;

    private static final int CODE_ING = 1;   //已发送，倒计时
    private static final int CODE_ING_END = 2;  //重新发送
    private static final int SMSDDK_HANDLER = 3;  //短信回调

    EventHandler eh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varify);
        ButterKnife.bind(this);

        initSmsSdk();

        if(!Config.IS_TEST) {
            rxBinding();
        }

        btnGetCode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void rxBinding() {
        RxTextView.textChanges(etPhoneNumber)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if(etPhoneNumber.getText().toString().equals("") || charSequence.equals("")){
                            btnGetCode.setEnabled(false);
                            btnNext.setEnabled(false);
                        }else{
                            btnGetCode.setEnabled(true);
                        }
                    }
                });

        RxTextView.textChanges(etCode)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if(etCode.getText().toString().equals("") || charSequence.equals("")){
                            btnNext.setEnabled(false);
                        }else{
                            btnNext.setEnabled(true);
                        }
                    }
                });
    }

    private void initSmsSdk() {
        SMSSDK.initSDK(this, Config.SMS_SDK_KEY, Config.SMS_SDK_SECRET);
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    //回调完成
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //验证码验证成功
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            ShowUtil.toast("验证成功！");
                            toRegisterActivity();
                        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            ShowUtil.toast("验证码发送成功");
                        }
                    } else if (result == SMSSDK.RESULT_ERROR) {
                        handleError((Throwable) data);
                    } else {
                        ShowUtil.toast("验证码输入有误，请重新获取验证码！");
                    }
                    break;
                case CODE_ING:
                    int seconds = msg.arg1;
                    btnGetCode.setText("重新获取(" + seconds + "秒)");
                    break;
                case CODE_ING_END:
                    btnGetCode.setEnabled(true);
                    btnGetCode.setText("重新获取");
                    break;
            }
        }
    };

    private void handleError(Throwable data) {
        try {
            Throwable throwable = data;
            throwable.printStackTrace();
            JSONObject object = new JSONObject(throwable.getMessage());
            String des = object.optString("detail");//错误描述
            int status = object.optInt("status");//错误代码
            if (status > 0 && !TextUtils.isEmpty(des)) {
                Toast.makeText(VarifyActivity.this, des, Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            //do something
        }
    }

    private void toRegisterActivity() {
        Intent intent = new Intent(VarifyActivity.this, RegisterActivity.class);
        intent.putExtra("phone_number" , etPhoneNumber.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                SMSSDK.getVerificationCode("86", etPhoneNumber.getText().toString());
                btnGetCode.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 60; i > 0; i--) {
                            Message msg = new Message();
                            msg.arg1 = i;
                            msg.what = CODE_ING;
                            handler.sendMessage(msg);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(CODE_ING_END);
                    }
                }).start();
                break;
            case R.id.btn_next:
                if(Config.IS_TEST){
                    toRegisterActivity();
                }else {
                    SMSSDK.submitVerificationCode("86", etPhoneNumber.getText().toString()
                            , etCode.getText().toString());//对验证码进行验证->回调函数
                }
                break;
        }
    }

    //todo:unregister event, prevent from out of memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
