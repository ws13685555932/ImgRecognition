package com.momo.imgrecognition.module.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.ButterKnife;
import io.reactivex.Observable;

public class ChangeInfoActivity extends BaseActivity {
    private String type;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);

        type = getIntent().getStringExtra("type");
        initFragment(type);
    }

    public void initFragment(String type) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);

        //todo:判断type为空
        if (type.equals(Config.TYPE_NAME) || type.equals(Config.TYPE_EMAIL)) {
            initNameFragment(bundle);
        } else {
            initDescrptionFragment();
        }
        mTransaction.commit();
    }

    private void initDescrptionFragment() {
        ChangeDescriptionFragment descriptionFragment = new ChangeDescriptionFragment();
        descriptionFragment.setToolbarListener(new ChangeDescriptionFragment.ToolbarListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onSave(String text) {
                updateDescription(text);
                ShowUtil.print(text);
            }
        });
        mTransaction.replace(R.id.container, descriptionFragment);
    }

    private void initNameFragment(Bundle bundle) {
        ChangeNameFragment nameFragment = new ChangeNameFragment();
        nameFragment.setArguments(bundle);
        nameFragment.setToolbarListener(new ChangeNameFragment.ToolbarListener() {
            @Override
            public void onBack() {
                finish();
            }

            @Override
            public void onSave(String type ,String text) {
                if(type.equals(Config.TYPE_NAME)){
                    updateName(text);
                }else{
                    updateEmail(text);
                }
                ShowUtil.print(text);
            }
        });
        mTransaction.replace(R.id.container, nameFragment);
    }


    private void updateDescription(final String text) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        DescriptionBean description = new DescriptionBean(text);
        description.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        description.setId((Integer) SharedUtil.getParam(UserConfig.USER_ID,0));
        Observable<ResponseInfo<UpdateResponse>> call =  userService.updateDescription(description);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        setResult(RESULT_OK, new Intent()
                                .putExtra("text", text));
                        finish();
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }

    private void updateName(final String text) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        NameBean name = new NameBean(text);
        name.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        name.setId((Integer) SharedUtil.getParam(UserConfig.USER_ID,0));
        Observable<ResponseInfo<UpdateResponse>> call =  userService.updateName(name);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        setResult(RESULT_OK, new Intent()
                                .putExtra("text", text));
                        finish();
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }

    private void updateEmail(final String text) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        EmailBean email = new EmailBean(text);
        email.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        email.setId((Integer) SharedUtil.getParam(UserConfig.USER_ID,0));
        Observable<ResponseInfo<UpdateResponse>> call =  userService.updateEmail(email);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        setResult(RESULT_OK, new Intent()
                                .putExtra("text", text));
                        finish();
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }


}
