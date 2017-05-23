package com.momo.imgrecognition.module.taglater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.taglater.adapter.TagListAdapter;
import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagLaterActivity extends BaseActivity {

    @BindView(R.id.lv_tag_later)
    ListView lvTagLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_later);
        ButterKnife.bind(this);

        List<TagLaterBean> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            TagLaterBean bean = new TagLaterBean();
            bean.setAdminName("管理员：momo1005");
            bean.setResId(R.drawable.bg_cover);
            bean.setTagStr("科学,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫");
            list.add(bean);
        }

        lvTagLater.setAdapter(new TagListAdapter(this,list));
    }
}
