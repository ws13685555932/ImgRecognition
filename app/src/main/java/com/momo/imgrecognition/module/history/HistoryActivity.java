package com.momo.imgrecognition.module.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.PictureService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.detail.ImageDetailActivity;
import com.momo.imgrecognition.module.detail.bean.IdRequest;
import com.momo.imgrecognition.module.detail.bean.PictureResponse;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.main.adapter.ImageAdapter;
import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;
import com.momo.imgrecognition.utils.TimeUtil;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.lv_history_list)
    RecyclerView lvHistoryList;

    private List<HistoryBean> historyList = new ArrayList<>();
    HistoryRecyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        getData();

//        List<HistoryBean> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            HistoryBean bean = new HistoryBean();
//            bean.setAdminName("管理员：momo1005" + i);
//            bean.setResId(R.drawable.bg_cover);
//            bean.setTagStr("#其他# 科学,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫");
//            bean.setTime(1494932883);
//            list.add(bean);
//        }

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        lvHistoryList.setLayoutManager(staggeredGridLayoutManager);
        adapter = new HistoryRecyAdapter(historyList,this);
        lvHistoryList.setAdapter(adapter);

    }

    int page = 1;

    private void getData() {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        HistoryRequest request = new HistoryRequest();
        request.setId((String) SharedUtil.getParam(UserConfig.USER_ID, ""));
        request.setLimit(10);
        request.setPage(page);

        Observable<ResponseInfo<HistoryResponse>> call = userService.getHistoryLabel(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<HistoryResponse>>io_main())
                .subscribe(new HttpObserver<HistoryResponse>() {
                    @Override
                    public void onSuccess(HistoryResponse historyResponse) {
                        page++;
                        showHistory(historyResponse);
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
    }

    private void showHistory(HistoryResponse historyResponse) {
        List<HistoryResponse.LabelListBean> picList = historyResponse.getLabelList();
        List<String> idList = new ArrayList<>();
        for (int i = 0; i < picList.size(); i++) {
            HistoryBean hist = new HistoryBean();
            HistoryResponse.LabelListBean bean = picList.get(i);
            if(idList.contains(bean.getPictureId())){
                continue;
            }
            hist.setTime(TimeUtil.timeStamp2Date(Long.valueOf(bean.getCreated_time())));
            String type = bean.getType();
            String label = bean.getLabel();
            String tagStr = "#" + type + "# " + label;
            hist.setTagStr(tagStr);
            hist.setTags(label);
            hist.setPicId(bean.getPictureId());
            idList.add(bean.getPictureId());
            getPicById(hist);
        }

    }

    private void getPicById(final HistoryBean bean) {
        IdRequest request = new IdRequest(bean.getPicId());
        PictureService pictureService = HttpManager.getInstance().createService(PictureService.class);
        Observable<ResponseInfo<PictureResponse>> call = pictureService.getPictureById(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<PictureResponse>>io_main())
                .subscribe(new HttpObserver<PictureResponse>() {
                    @Override
                    public void onSuccess(PictureResponse pictureResponse) {
                        bean.setPicUrl(pictureResponse.getPath());
                        ShowUtil.print(bean.getPicUrl());
                        historyList.add(bean);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.toast(message);
                    }
                });
    }
}
