package com.momo.imgrecognition.module.category;

import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.PictureService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.module.category.bean.CateRequest;
import com.momo.imgrecognition.module.category.bean.CateResponse;
import com.momo.imgrecognition.module.main.bean.PictureRequest;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.ShowUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.recycler_cate)
    RecyclerView recyclerCate;

    int page = 1;
    List<CateImageBean> cateList = new ArrayList<>();
    CateAdapter cateAdapter;
    private boolean isNeedRefresh = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        final String category = getIntent().getStringExtra("category");
        title.setText(category);

        initData(category);
        

        cateAdapter = new CateAdapter(cateList,this);
        cateAdapter.setOnRefreshListener(new CateAdapter.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNeedRefresh) {
                    initData(category);
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerCate.setLayoutManager(gridLayoutManager);
        recyclerCate.setAdapter(cateAdapter);

//        recyclerCate.setOnScrollChangeListener((new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
//                int totalItemCount = mLayoutManager.getItemCount();
//                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
//                // dy>0 表示向下滑动
//                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
//                    if(isLoadingMore){
//                        Log.d(TAG,"ignore manually update!");
//                    } else{
//                        loadPage();//这里多线程也要手动控制isLoadingMore
//                        isLoadingMore = false;
//                    }
//                }
//            }
//        }););
    }

    private void initData(final String category) {
        final CateRequest request = new CateRequest();
        request.setLimit(10);
        request.setPage(page);
        request.setType(category);

        PictureService pictureService = HttpManager.getInstance().createService(PictureService.class);
        Observable<ResponseInfo<CateResponse>> call =  pictureService.getPictureByType(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<CateResponse>>io_main())
                .subscribe(new HttpObserver<CateResponse>() {
                    @Override
                    public void onSuccess(CateResponse cateResponse) {
                        page++;
                        if (cateResponse.getPictureList().size() < request.getLimit()) {
                            isNeedRefresh = false;
                        }
                        handleMessage(cateResponse.getPictureList());
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }

    private void handleMessage(List<CateResponse.PictureListBean> pictureList) {
        for (int i = 0; i < pictureList.size(); i++) {
            CateResponse.PictureListBean bean = pictureList.get(i);
            CateImageBean cate = new CateImageBean();
            String tags = bean.getAcceptedLabel();

            if (tags != null) {
                String[] splitArr = tags.split(",");
                cate.setTagCont(tags);
                cate.setTagNum(splitArr.length);
            }else{
                cate.setTagCont("该图片还没有标签");
                cate.setTagNum(0);
            }
            cate.setPicId(bean.getPictureId());
            cate.setTagAdmin("By:" + bean.getManagerName());
            cate.setImgUrl(bean.getPath());
            cateList.add(cate);

        }
        cateAdapter.notifyDataSetChanged();

    }
}
