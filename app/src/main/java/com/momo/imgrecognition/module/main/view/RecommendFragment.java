package com.momo.imgrecognition.module.main.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private List<ImageBean> imageBeanList = new ArrayList<>();
    private int[] resId = new int[]{R.drawable.bg_sample_one, R.drawable.bg_sample_two, R.drawable.bg_sample_three, R.drawable.bg_sample_four
                , R.drawable.bg_sample_five, R.drawable.bg_sample_six, R.drawable.bg_sample_seven};
    private String[] resName = new String[]{"onedfhgsdf",
            "twozcbzsgadgadasdhfvdsvdsfeahfaiunfadsngs",
            "threegafdsfasdfa",
            "fouradgafdfasdfasdfasdfasgs",
            "fivaeasgdafdfasfas",
            "sixasdf"
            ,"bg_sample_seven"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        unbinder = ButterKnife.bind(this, view);

        for(int i=0;i<20;i++) {
            ImageBean bean = new ImageBean();
            bean.setImgId(resId[i%7]);
            bean.setName(resName[i%7]);
            imageBeanList.add(bean);
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycle.setLayoutManager(staggeredGridLayoutManager);
        recycle.setAdapter(new ImageAdapter(imageBeanList,getContext()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
