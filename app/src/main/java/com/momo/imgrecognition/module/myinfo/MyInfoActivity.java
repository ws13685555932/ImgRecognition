package com.momo.imgrecognition.module.myinfo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.crop.UCrop;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.utils.ShowUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODE = 2;

    @BindView(R.id.rl_user_icon)
    RelativeLayout rlUserIcon;
    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    private BottomSheetDialog pickDialog;
    private String mTempPhotoPath;
    private Uri mDestinationUri;

    int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);

        mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";
        mDestinationUri = Uri.fromFile(new File(getCacheDir(), "cropImage.jpeg"));
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
                    ShowUtil.print("camera success!");
                    break;
                case GALLERY_REQUEST_CODE:
                    startCropActivity(data.getData());
                    break;
                case UCrop.REQUEST_CROP:
                    ShowUtil.print("crop!");
                    handleCropResult(data);
                    break;
                case UCrop.RESULT_ERROR:
                    ShowUtil.print("error!");
                    handleCropError(data);
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
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ivUserIcon.setImageBitmap(bitmap);
            ShowUtil.toast("更换成功");
        } else {
            ShowUtil.toast("无法剪切选择图片");
        }
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

    @OnClick(R.id.rl_birthday)
    public void onBirthdayClicked() {

        DatePickerDialog pickDateDialog = new DatePickerDialog(this, R.style.MyDatePickerDialog,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                displayBirthday();
            }
        }, mYear, mMonth, mDay);
        pickDateDialog.show();

    }

    private void displayBirthday() {
        String birthdayStr = mYear + "-" + mMonth + "-" + mDay;
        tvBirthday.setText(birthdayStr);
    }
}
