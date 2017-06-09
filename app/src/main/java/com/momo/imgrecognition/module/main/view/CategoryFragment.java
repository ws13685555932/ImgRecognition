package com.momo.imgrecognition.module.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.main.adapter.CategoryAdapter;
import com.momo.imgrecognition.module.main.bean.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/21.
 */

public class CategoryFragment extends Fragment {

    @BindView(R.id.grid)
    GridView grid;
    Unbinder unbinder;

    private String[] cateStrs = new String[]{
        "动物", "风景", "建筑", "健康",
        "生活", "体育", "人物", "文化",
        "教育", "娱乐", "植物", "其他"
    };

    private int[] resId = new int[]{
            R.drawable.ic_cate_dongwu,
            R.drawable.ic_cate_fengjing,
            R.drawable.ic_cate_jianzhu,
            R.drawable.ic_cate_yiliao,

            R.drawable.ic_cate_shenghuo,
            R.drawable.ic_cate_tiyu,
            R.drawable.ic_cate_renwu,
            R.drawable.ic_cate_wenhua,

            R.drawable.ic_cate_xueshu,
            R.drawable.ic_cate_yule,
            R.drawable.ic_cate_zhiwu,
            R.drawable.ic_cate_qita
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categroy, container, false);
        unbinder = ButterKnife.bind(this, view);
        List<CategoryBean> list = new ArrayList<>();
        for(int i=0;i<12;i++){
            CategoryBean bean = new CategoryBean(resId[i],cateStrs[i]);
            list.add(bean);
        }
        grid.setAdapter(new CategoryAdapter(getActivity(),list));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
