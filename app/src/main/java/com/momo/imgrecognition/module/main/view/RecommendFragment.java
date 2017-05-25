package com.momo.imgrecognition.module.main.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.adapter.ImageAdapter;
import com.momo.imgrecognition.module.main.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/21.
 */

public class RecommendFragment extends Fragment {

    @BindView(R.id.recycle)
    RecyclerView recycle;
    Unbinder unbinder;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private ImageAdapter mAdapter;
    private List<ImageBean> imageBeanList = new ArrayList<>();
    private int[] resId = new int[]{R.drawable.bg_sample_one, R.drawable.bg_sample_two, R.drawable.bg_sample_three, R.drawable.bg_sample_four
            , R.drawable.bg_sample_five, R.drawable.bg_sample_six, R.drawable.bg_sample_seven};
    private String[] resName = new String[]{"onedfhgsdf",
            "twozcbzsgadgadasdhfvdsvdsfeahfaiunfadsngs",
            "threegafdsfasdfa",
            "fouradgafdfasdfasdfasdfasgs",
            "fivaeasgdafdfasfas",
            "sixasdf"
            , "bg_sample_seven"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        unbinder = ButterKnife.bind(this, view);



        initPicture();
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycle.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new ImageAdapter(imageBeanList, getContext());

        recycle.setAdapter(mAdapter);


        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.theme_color_primary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        return view;
    }



    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPicture();
                        mAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initPicture() {
        List<ImageBean> imgList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ImageBean bean = new ImageBean();
            bean.setImgUrl(resId[i % 7]);
            bean.setTags(resName[i % 7]);
            bean.setTagNum(10);
            imgList.add(bean);
        }
        imageBeanList.addAll(0,imgList);

//        PictureService pictureService = HttpManager.getInstance().createService(PictureService.class);
//        PictureRequest request = new PictureRequest();
//        request.setLimit(20);
//        request.setId((int) SharedUtil.getParam(UserConfig.USER_ID,0));
//        Observable<ResponseInfo<RecomResponse>> call = pictureService.getPicture(request);
//        call.compose(RxSchedulersHelper.<ResponseInfo<RecomResponse>>io_main())
//                .subscribe(new HttpObserver<RecomResponse>() {
//                    @Override
//                    public void onSuccess(RecomResponse recomResponse) {
//                        List<ImageBean> imgList = new ArrayList<>();
//                        ArrayList<RecomResponse.PictureListBean> pictureList =
//                                (ArrayList<RecomResponse.PictureListBean>) recomResponse.getPictureList();
//                        for (int i = 0; i < pictureList.size(); i++) {
//                            RecomResponse.PictureListBean picture = pictureList.get(i);
//                            ImageBean bean = new ImageBean();
//                            bean.setImgUrl(picture.getPath());
//                            bean.setName(resName[i % 7]);
//                            imgList.add(bean);
//                        }
//                        imageBeanList.addAll(0,imgList);
//                        ShowUtil.toast("更新了20条内容！");
//                    }
//
//                    @Override
//                    public void onFailed(String message) {
//
//                    }
//                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
