package com.momo.imgrecognition.module.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.lv_history_list)
    ListView lvHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        List<HistoryBean> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            HistoryBean bean = new HistoryBean();
            bean.setAdminName("管理员：momo1005");
            bean.setResId(R.drawable.bg_cover);
            bean.setTags("标签：科学,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫");
            bean.setTime(1494932883);
            list.add(bean);
        }

        lvHistoryList.setAdapter(new HistoryAdapter(this,list));

    }
}
