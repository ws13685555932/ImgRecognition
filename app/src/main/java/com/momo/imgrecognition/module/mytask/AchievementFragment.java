package com.momo.imgrecognition.module.mytask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.momo.imgrecognition.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/17.
 */

public class AchievementFragment extends Fragment {

    @BindView(R.id.lv_achievement)
    ListView lvAchievement;
    Unbinder unbinder;
    private List<TaskBean> achievementList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        unbinder = ButterKnife.bind(this, view);
        String[] titles2 = new String[]{"签到之星", "签到狂魔"
                , "标签勇士", "标签先锋", "标签之王","标签缔造者","标签毁灭者"
                , "青铜标签师","白银标签师","黄金标签师","宗师标签师"};
        String[] dess2 = new String[]{"连续签到7天，额外奖励7积分", "连续签到1个月，额外奖励30积分"
                , "已贴标签达到20", "已贴标签达到100", "已贴标签达到200","已贴标签达到500","已贴标签达到1000"
                ,"被采纳5个标签","被采纳10个标签","被采纳50个标签","被采纳100个标签"};
        int[] progress2 = new int[]{2, 27, 18, 18, 18 ,18 , 18 ,3,5,40,5};
        int[] totals2 = new int[]{7, 30, 20, 100 ,200, 500,1000,5,10,50,100};

        for (int i = 0; i < titles2.length; i++) {
            TaskBean task = new TaskBean();
            task.setTitle(titles2[i]);
            task.setDes(dess2[i]);
            task.setTotal(totals2[i]);
            task.setProgress(progress2[i]);
            achievementList.add(task);
        }
        View head = LayoutInflater.from(getContext()).inflate(R.layout.header_list,null);
        TextView tvLabel = (TextView) head.findViewById(R.id.label);
        tvLabel.setText("得成就，拿积分");
        lvAchievement.addHeaderView(head);
        lvAchievement.setAdapter(new TaskAdapter(getContext(), achievementList));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
