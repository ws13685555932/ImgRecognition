package com.momo.imgrecognition.module.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.PictureService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.customedview.InfoDialog;
import com.momo.imgrecognition.customedview.TagDeleteDialog;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.detail.bean.IdRequest;
import com.momo.imgrecognition.module.detail.bean.PictureResponse;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;


// TODO: 2017/5/16 添加历史输入标签
public class ImageDetailActivity extends BaseActivity {

    @BindView(R.id.tfl_labels)
    TagFlowLayout tflLabels;
    @BindView(R.id.tfl_history_labels)
    TagFlowLayout tflHistoryLabels;
    @BindView(R.id.fltbtn_confirm)
    FloatingActionButton fabConfirm;
    @BindView(R.id.et_customed_tag)
    EditText etCustomedTag;


    @BindView(R.id.ll_customed_tag)
    LinearLayout llCustomedTag;
    @BindView(R.id.tv_add_customed_tag)
    TextView tvAddCustomedTag;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    RelativeLayout mRoot;

    List<String> tagList;
    List<String> hisList;

    TagAdapter tagAdapter;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_choose_tags)
    TextView tvChooseTags;

    private boolean isInput = false;

    int limitPic;
    int chooseNum = 0;

    private int limit[] = new int[]{
            1, 1, 2, 3, 4, 4, 5, 6, 7, 9
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        initData();

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        llCustomedTag.setVisibility(View.INVISIBLE);

        mRoot = (RelativeLayout) findViewById(R.id.root);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mRoot.getWindowVisibleDisplayFrame(r);
                //r.top 是状态栏高度
                int screenHeight = mRoot.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom;

                if (softHeight > 100) {//当输入法高度大于100判定为输入法打开了
                    isInput = true;
                    llCustomedTag.setTranslationY(-softHeight);
                    fadeOut(fabConfirm);
//                    fadeIn(llCustomedTag);
                    llCustomedTag.setVisibility(View.VISIBLE);
                    llCustomedTag.setTranslationY(-softHeight);
                    fadeIn(llCustomedTag);
//                    tranlateUp(-softHeight);


                } else if (isInput) {//否则判断为输入法隐藏了w
                    isInput = false;
                    llCustomedTag.setVisibility(View.GONE);
                    llCustomedTag.setTranslationY(softHeight);
                    fadeIn(fabConfirm);
                }
            }
        });

        final String[] labels = new String[]{};
        tagList = new ArrayList<>(Arrays.asList(labels));
//        final String[] labels2 = new String[]{"android", "java", "php", "hello", "world", "hhh"};
        hisList = new ArrayList<>();

        tagAdapter = new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this)
                        .inflate(R.layout.layout_labels, parent, false);
                tvLabel.setText(s);

                tvLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showInfo(position);
                    }
                });
                return tvLabel;
            }
        };

        tflLabels.setAdapter(tagAdapter);

        tvAddCustomedTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

//                WindowManager.LayoutParams lp= getWindow().getAttributes();
//                lp.alpha =  0.6f;
//                getWindow().setAttributes(lp);
//                llCustomedTag.getBackground().setAlpha(255);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llCustomedTag.setVisibility(View.INVISIBLE);
                String tag = etCustomedTag.getText().toString();
                tagList.add(tag);
                chooseNum ++;
                 updateChooseTag();
                if(chooseNum == limitPic){
                    setCustomedTagEnable(false);
                }
                tagAdapter.notifyDataChanged();

                InputMethodManager m = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                etCustomedTag.setText("");
            }
        });

        fabConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowUtil.toast("提交！");
            }
        });

        tflHistoryLabels.setAdapter(new TagAdapter<String>(hisList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tvLabel = (TextView) LayoutInflater.from(ImageDetailActivity.this)
                        .inflate(R.layout.layout_normal_label, parent, false);
                tvLabel.setText(s);
                return tvLabel;
            }
        });

        tflHistoryLabels.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String tag = hisList.get(position);
                if (!tagList.contains(tag)) {
                    if(chooseNum == limitPic){
                        ShowUtil.toast("您添加的标签数目已达当前等级的上限");
                        return true;
                    }
                    tagList.add(tag);
                    chooseNum ++ ;
                    if(chooseNum == limitPic){
                        setCustomedTagEnable(false);
                    }
                    updateChooseTag();
                    tagAdapter.notifyDataChanged();
                }else{
                    ShowUtil.toast("您已添加过该标签");
                }
                return true;
            }
        });
    }

    private void showInfo(final int position) {
        TagDeleteDialog dialog = new TagDeleteDialog();
        dialog.setOnConfirmListener(new InfoDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                tagList.remove(position);
                chooseNum -- ;
                updateChooseTag();
                setCustomedTagEnable(true);
                tagAdapter.notifyDataChanged();
            }
        });
        dialog.show(getSupportFragmentManager(), TagDeleteDialog.TAG);
    }

    String url;

    private void initData() {
        

        // TODO: 2017/6/9 test level
//        int level = (int) SharedUtil.getParam(UserConfig.USER_LEVEL, 0);
        int level = 5;
        if (level != 0) {
            limitPic = limit[level - 1];
        } else {
            limitPic = 1;
        }
        updateChooseTag();


        String id = getIntent().getStringExtra("imageId");
        IdRequest request = new IdRequest(id);
        getImageData(request);

    }

    private void getImageData(IdRequest request) {
        PictureService pictureService = HttpManager.getInstance().createService(PictureService.class);
        Observable<ResponseInfo<PictureResponse>> call = pictureService.getPictureById(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<PictureResponse>>io_main())
                .subscribe(new HttpObserver<PictureResponse>() {
                    @Override
                    public void onSuccess(PictureResponse pictureResponse) {
                        String imgUrl = pictureResponse.getPath();
                        ShowUtil.print(imgUrl);
                        Glide.with(ImageDetailActivity.this).load(imgUrl).into(ivImage);
                        String tags = pictureResponse.getResultLabel();
                        if (tags != null) {
                            String[] splitArr = tags.split(",");
                            List<String> list = new ArrayList<String>(Arrays.asList(splitArr));
                            tagList.clear();
                            tagList.addAll(list);
                        }
                    }

                    @Override
                    public void onFailed(String message) {
                            ShowUtil.toast(message);
                    }
                });

    }


    @OnClick(R.id.iv_image)
    public void onViewClicked() {
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra("url", url);
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        ivImage, getString(R.string.transName));
        ActivityCompat.startActivity(this, intent, compat.toBundle());

//        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int height = getStatusBarHeight();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
        params.topMargin = height;
        toolbar.setLayoutParams(params);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void fadeIn(View view) {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        animatorSet.setDuration(300);
        animatorSet.play(scaleX).with(scaleY).with(fadeIn);
        animatorSet.start();
    }

    public void fadeOut(View view) {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1, 0);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        animatorSet.setDuration(300);
        animatorSet.play(scaleX).with(scaleY).with(fadeOut);
        animatorSet.start();
    }

    private void tranlateUp(int height) {
        ObjectAnimator tranlateUp = ObjectAnimator.ofFloat(llCustomedTag, "translationY", 0, height);
        tranlateUp.setDuration(350);
        tranlateUp.start();
    }

    private void updateChooseTag(){
        tvChooseTags.setText("您认为合适的标签（" + chooseNum + "/" + limitPic + "）");
    }

    private void setCustomedTagEnable(boolean enable){
        if (!enable) {
            tvAddCustomedTag.setTextColor(getResources().getColor(R.color.gray_dark));
            tvAddCustomedTag.setClickable(false);
        }else{
            tvAddCustomedTag.setTextColor(getResources().getColor(R.color.theme_color_primary));
            tvAddCustomedTag.setClickable(true);
        }

    }


}
