package com.momo.imgrecognition.module.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.ButterKnife;
import io.reactivex.Observable;

public class ChangeInfoActivity extends AppCompatActivity {
    private String type;
    private MyInfoBean myInfo;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);

        type = getIntent().getStringExtra("type");
        myInfo = (MyInfoBean) getIntent().getSerializableExtra("myinfo");
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
//                setResult(RESULT_OK, new Intent()
//                        .putExtra("text", text));
                changeinfo(text);
                ShowUtil.print(text);
//                finish();
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
//                setResult(RESULT_OK, new Intent()
//                        .putExtra("text", text));
                changeInfo(type,text);
                ShowUtil.print(text);
//                finish();
            }
        });
        mTransaction.replace(R.id.container, nameFragment);
    }
    private void changeinfo(String text) {
        if(type != null){
            myInfo.setIntroduction(text);
        }

        updateInfo(myInfo);
    }

    private void changeInfo(String type, String text) {
        if(type!=null){
            if(type.equals(Config.TYPE_NAME)){
                myInfo.setName(text);
            }else if(type.equals(Config.TYPE_EMAIL)){
                myInfo.setEmail(text);
            }
        }

        updateInfo(myInfo);
    }

    private void updateInfo(final MyInfoBean myInfo) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        Observable<ResponseInfo<MyInfoBean>> call =userService.updateInfo(myInfo);
        call.compose(RxSchedulersHelper.<ResponseInfo<MyInfoBean>>io_main())
                .subscribe(new HttpObserver<MyInfoBean>() {
                    @Override
                    public void onSuccess(MyInfoBean myInfoBean) {
                        if(type != null){
                            if(type . equals(Config.TYPE_NAME)){
                                setResult(RESULT_OK, new Intent()
                                        .putExtra("text", myInfo.getName()));
                            }else if(type.equals(Config.TYPE_EMAIL)){
                                setResult(RESULT_OK, new Intent()
                                        .putExtra("text", myInfo.getEmail()));
                            }else{
                                setResult(RESULT_OK, new Intent()
                                        .putExtra("text", myInfo.getIntroduction()));
                            }
                        }
                        finish();
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });

    }


}
