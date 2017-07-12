package com.momo.imgrecognition.module.myinfo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.crop.UCrop;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.customedview.InfoDialog;
import com.momo.imgrecognition.customedview.PickSexDialog;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.login.LoginActivity;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.main.MainActivity;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.QiniuUtil;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODE = 2;
    private static final int CHANGE_NAME_REQUEST_CODE = 3;
    private static final int CHANGE_EMAIL_REQUEST_CODE = 4;
    private static final int CHANGE_DESCRIPTION_REQUEST_CODE = 5;

    @BindView(R.id.rl_user_icon)
    RelativeLayout rlUserIcon;
    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.rl_user_name)
    RelativeLayout rlUserName;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.rl_email)
    RelativeLayout rlEmail;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.rl_description)
    RelativeLayout rlDescription;
    @BindView(R.id.rl_log_out)
    RelativeLayout rlLogOut;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    private BottomSheetDialog pickDialog;
    private String mTempPhotoPath;
    private Uri mDestinationUri;

    int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);



        initData();
    }

    private void initData() {
//        mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";
        String tempFilePath = Config.TEMP_FILE_PATH;
        File file = new File(tempFilePath);
        if(!file .exists()){
            file.mkdirs();
        }
        mTempPhotoPath = tempFilePath + File.separator + "photo.jpeg";
        mDestinationUri = Uri.fromFile(new File(tempFilePath, "userIcon.jpeg"));
        
        
        getUserInfo();

    }

    private void getUserInfo() {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        UserRequest request = new UserRequest();
        request.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN, ""));
        request.setId((String) SharedUtil.getParam(UserConfig.USER_ID, ""));
        request.setName((String) SharedUtil.getParam(UserConfig.USER_NAME, ""));
        ShowUtil.print(request.toString());
        Observable<ResponseInfo<UserInfo>> call = userService.getUserInfo(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<UserInfo>>io_main())
                .subscribe(new HttpObserver<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        tvNickname.setText(userInfo.getName());
                        tvSex.setText(userInfo.getSex());
                        tvBirthday.setText(userInfo.getBirthday());
                        tvPhoneNumber.setText(userInfo.getPhone());
                        tvEmail.setText(userInfo.getEmail());
                        tvDescription.setText(userInfo.getIntroduction());
                        if(userInfo.getAvatarUrl()!=null) {
                            Glide.with(MyInfoActivity.this)
                                    .load(userInfo.getAvatarUrl())
                                    .into(ivUserIcon);
                        }

                    }

                    // TODO: 2017/5/24 默认显示界面 
                    @Override
                    public void onFailed(String message) {
                        ShowUtil.print(message);
                    }
                });
    }

    @OnClick(R.id.rl_user_icon)
    public void onUserIconClicked() {
        pickDialog = new BottomSheetDialog(this);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_pick, null);
        pickDialog.setContentView(contentView);
        pickDialog.show();


        TextView tvFromCamera = (TextView) contentView.findViewById(R.id.tv_from_camera);
        TextView tvFromGallery = (TextView) contentView.findViewById(R.id.tv_from_gallery);
        TextView tvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);

        tvFromCamera.setOnClickListener(this);
        tvFromGallery.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_from_camera:
                takePhoto();
                break;
            case R.id.tv_from_gallery:
                pickFromGallery();
                break;
            case R.id.tv_cancel:
                pickDialog.dismiss();
                break;
        }

    }

    private void pickFromGallery() {
        //TODO:运行时权限
        pickDialog.dismiss();
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
    }

    private void takePhoto() {
        //TODO:运行时权限
        pickDialog.dismiss();
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempPhotoPath)));
        startActivityForResult(takeIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    File tempFile = new File(mTempPhotoPath);
                    startCropActivity(Uri.fromFile(tempFile));
                    break;
                case GALLERY_REQUEST_CODE:
                    startCropActivity(data.getData());
                    break;
                case UCrop.REQUEST_CROP:
                    handleCropResult(data);
                    break;
                case UCrop.RESULT_ERROR:
                    handleCropError(data);
                    break;

                case CHANGE_NAME_REQUEST_CODE:
                    String nickname = data.getExtras().getString("text");
                    SharedUtil.saveParam(UserConfig.USER_NAME, nickname);
                    tvNickname.setText(nickname);
                    break;
                case CHANGE_EMAIL_REQUEST_CODE:
                    String email = data.getExtras().getString("text");
                    tvEmail.setText(email);
                    break;
                case CHANGE_DESCRIPTION_REQUEST_CODE:
                    String desp = data.getExtras().getString("text");
                    tvDescription.setText(desp);
                    break;
            }
        }

    }


    private void handleCropError(Intent result) {
        deleteTempPhotoFile();
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            ShowUtil.print("handleCropError: " + cropError);
            ShowUtil.toast(cropError.getMessage());
        } else {
            ShowUtil.toast("无法剪切选择图片!");
        }
    }

    //todo:sava bitmap in storage by sharedpreference for mainactivity show user icon
    private void handleCropResult(Intent result) {
        deleteTempPhotoFile();
        final Uri resultUri = UCrop.getOutput(result);
        if (null != resultUri) {
            uploadIcon(resultUri);
        } else {
            ShowUtil.toast("无法剪切选择图片");
        }
    }

    private void uploadIcon(Uri resultUri) {
        String filePath = resultUri.toString();
        filePath = filePath.substring(7,filePath.length());
        ShowUtil.print(filePath);
        QiniuUtil.uploadFile(filePath, new QiniuUtil.UploadListener() {
            @Override
            public void onUploadSuccess(String url) {
                SharedUtil.saveParam(UserConfig.USER_ICON_URL,url);
                updateIcon(url);
            }

            @Override
            public void onUploadFail(Error error) {

            }
        });
    }

    private void updateIcon(final String url) {
        UserService userService = HttpManager.getInstance()
                .createService(UserService.class);
        UrlBean urlBean = new UrlBean();
        urlBean.setAvatarUrl(url);
        urlBean.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        urlBean.setId((String) SharedUtil.getParam(UserConfig.USER_ID,""));
        Observable<ResponseInfo<UpdateResponse>> call = userService.updateUserAvatar(urlBean);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        Glide.with(MyInfoActivity.this)
                                .load(url)
                                .into(ivUserIcon);
                        SharedUtil.saveParam(UserConfig.USER_ICON_URL,url);
                        ShowUtil.toast("更换成功");
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast("头像上传失败");
                    }
                });
    }

    private void deleteTempPhotoFile() {
        File tempFile = new File(mTempPhotoPath);
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }

    private void startCropActivity(Uri uri) {
        UCrop.of(uri, mDestinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(512, 512)
                .withTargetActivity(CropActivity.class)
                .start(this);
    }

//    @OnClick(R.id.rl_birthday)
//    public void onBirthdayClicked() {
//
//        DatePickerDialog pickDateDialog = new DatePickerDialog(this, R.style.MyDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                mYear = year;
//                mMonth = month;
//                mDay = day;
//                displayBirthday();
//            }
//        }, mYear, mMonth, mDay);
//        pickDateDialog.show();
//
//    }

    @OnClick({R.id.rl_user_name, R.id.rl_email, R.id.rl_description
            ,R.id.rl_sex, R.id.rl_birthday , R.id.rl_log_out})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_user_name:
                changeInfo(Config.TYPE_NAME);
                break;
            case R.id.rl_email:
               changeInfo(Config.TYPE_EMAIL);
                break;
            case R.id.rl_description:
               changeInfo(Config.TYPE_DESCRIPTION);
                break;
            case R.id.rl_sex:
                changeSex();
                break;
            case R.id.rl_birthday:
                changeBirthday();
                break;
            case R.id.rl_log_out:
                logOut();
                break;
        }
    }

    private void logOut() {
        final InfoDialog dialog = new InfoDialog();

        dialog.show(getSupportFragmentManager(),dialog.TAG);

        dialog.setOnConfirmListener(new InfoDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {

                clearData();

                Intent intent = new Intent(MyInfoActivity.this, LoginActivity.class);
                intent.putExtra(UserConfig.USER_LOGIN,"login_password");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void clearData() {
        SharedUtil.saveParam(UserConfig.USER_ID,"");
        SharedUtil.saveParam(UserConfig.USER_LEVEL,0);
        SharedUtil.saveParam(UserConfig.USER_ICON_URL,"");
        SharedUtil.saveParam(UserConfig.USER_NAME,"");
        SharedUtil.saveParam(UserConfig.USER_PASSWORD,"");
        SharedUtil.saveParam(UserConfig.USER_TOKEN,"");
        SharedUtil.clearList(UserConfig.HISTORY_SEARCH);
        SharedUtil.clearList(UserConfig.HISTORY_LABELS);
    }

    private void changeInfo(String type){
        Intent intent = new Intent(this,ChangeInfoActivity.class);
        intent.putExtra("type",type);
        if(type!= null){
            if(type .equals(Config.TYPE_NAME)){
                startActivityForResult(intent,CHANGE_NAME_REQUEST_CODE);
            }else if(type.equals(Config.TYPE_EMAIL)){
                startActivityForResult(intent,CHANGE_EMAIL_REQUEST_CODE);
            }else{
                startActivityForResult(intent,CHANGE_DESCRIPTION_REQUEST_CODE);
            }
        }
    }

    private void changeBirthday() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        mYear = c.get(Calendar.YEAR);
        DatePickerDialog pickDateDialog = new DatePickerDialog(this, R.style.MyDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month+1;
                mDay = day;
                updateBirthday();
            }
        }, mYear, mMonth, mDay);
        pickDateDialog.show();
    }

    private void updateBirthday() {
        String birthdayStr = mYear + "-" + mMonth + "-" + mDay;
        ShowUtil.print(birthdayStr);
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        BirthdayBean birthday = new BirthdayBean(birthdayStr);
        birthday.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        birthday.setId((String) SharedUtil.getParam(UserConfig.USER_ID,""));
        Observable<ResponseInfo<UpdateResponse>> call =  userService.updateBirthday(birthday);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        String birthdayString = mYear + "-" + mMonth + "-" + mDay;
                        tvBirthday.setText(birthdayString);
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }

    private void changeSex() {
        final PickSexDialog pickSexDialog = new PickSexDialog();
        pickSexDialog.show(getSupportFragmentManager(), PickSexDialog.TAG);
        pickSexDialog.setClickListener(new PickSexDialog.ClickListener() {
            @Override
            public void onSexPicked(String sex) {
                updateSex(sex);

            }
        });
    }

    private void updateSex(final String text) {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        SexBean sex = new SexBean(text);
        sex.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN,""));
        sex.setId((String) SharedUtil.getParam(UserConfig.USER_ID,""));
        Observable<ResponseInfo<UpdateResponse>> call =  userService.updateSex(sex);
        call.compose(RxSchedulersHelper.<ResponseInfo<UpdateResponse>>io_main())
                .subscribe(new HttpObserver<UpdateResponse>() {
                    @Override
                    public void onSuccess(UpdateResponse updateResponse) {
                        tvSex.setText(text);
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }

}
