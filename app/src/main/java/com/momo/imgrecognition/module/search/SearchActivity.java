package com.momo.imgrecognition.module.search;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.PictureService;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.category.CateAdapter;
import com.momo.imgrecognition.module.category.CateImageBean;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_extend)
    LinearLayout llExtend;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    CateAdapter adapter;
    List<CateImageBean> searchList = new ArrayList<>();
    @BindView(R.id.tv_info)
    TextView tvInfo;

    SearchAdpter adpter;
    List<String> searchHisList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchHisList = SharedUtil.getDataList(UserConfig.HISTORY_SEARCH);
        adpter = new SearchAdpter(this, searchHisList);
        lvSearch.setAdapter(adpter);

        adapter = new CateAdapter(searchList, this);
        recycle.setAdapter(adapter);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(grid);


        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                llExtend.setVisibility(View.GONE);
                searchPicture(searchHisList.get(i));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.iv_back, R.id.et_search, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.et_search:
                llExtend.setVisibility(View.VISIBLE);
                searchHisList = SharedUtil.getDataList(UserConfig.HISTORY_SEARCH);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_search:
                llExtend.setVisibility(View.GONE);
                recycle.setVisibility(View.GONE);
                String searchStr = etSearch.getText().toString();
                if (!searchStr.equals("")) {
                    ArrayList<String> list = SharedUtil.getDataList(UserConfig.HISTORY_SEARCH);
                    if (list != null && !list.contains(searchStr)) {
                        list.add(searchStr);
                    }
                    SharedUtil.saveDataList(UserConfig.HISTORY_SEARCH, list);
                    searchPicture(etSearch.getText().toString());
                    ShowUtil.toast("搜索中");
                } else {
                    ShowUtil.toast("搜索内容不能为空");
                }
                break;
        }
    }

    int mPage = 1;

    private void searchPicture(String text) {
        SearchRequest request = new SearchRequest();
        request.setLimit(10);
        request.setPage(mPage);
        request.setSearch(text);
        PictureService pictureService = HttpManager.getInstance().createService(PictureService.class);
        Observable<ResponseInfo<SearchReponse>> call = pictureService.searchPicture(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<SearchReponse>>io_main())
                .subscribe(new HttpObserver<SearchReponse>() {
                    @Override
                    public void onSuccess(SearchReponse searchReponse) {
                        mPage++;
                        if (searchReponse.getPictureList().size() == 0) {
                            tvInfo.setVisibility(View.VISIBLE);
                        } else {
                            tvInfo.setVisibility(View.GONE);
                            recycle.setVisibility(View.VISIBLE);
                        }
                        handleData(searchReponse.getPictureList());
                    }

                    @Override
                    public void onFailed(String message) {
                        tvInfo.setVisibility(View.VISIBLE);
                        ShowUtil.toast(message);
                    }
                });
    }

    private void handleData(List<SearchReponse.PictureListBean> list) {
        for (SearchReponse.PictureListBean picture : list) {
            ShowUtil.print(picture.toString());
            CateImageBean bean = new CateImageBean();
            bean.setPicId(picture.getPictureId());
            bean.setTagAdmin(picture.getManagerName());

            String tags = picture.getAcceptedLabel();
            if (tags != null) {
                String[] splitArr = tags.split(",");
                bean.setTagCont(tags);
                bean.setTagNum(splitArr.length);
            } else {
                bean.setTagCont("该图片还没有标签");
                bean.setTagNum(0);
            }
            bean.setImgUrl(picture.getPath());
            searchList.add(bean);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        if (llExtend.getVisibility() == View.VISIBLE) {
            llExtend.setVisibility(View.GONE);
        } else {
            finish();
        }
    }
}
