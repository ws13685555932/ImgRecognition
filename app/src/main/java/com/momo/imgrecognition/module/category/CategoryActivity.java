package com.momo.imgrecognition.module.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.recycler_cate)
    RecyclerView recyclerCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        Random r = new Random();
        List<CateImageBean> list = new ArrayList<>();
        for(int i=0;i<15;i++){
            CateImageBean bean = new CateImageBean();
            bean.setResUrl(R.drawable.bg_sample_five);
            bean.setTagAdmin("哈哈哈哈");
            bean.setTagCont("这是一个标签标题");
            bean.setTagNum(r.nextInt(10));
            list.add(bean);
        }

        CateAdapter cateAdapter = new CateAdapter(list,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerCate.setLayoutManager(gridLayoutManager);
        recyclerCate.setAdapter(cateAdapter);
    }
}
