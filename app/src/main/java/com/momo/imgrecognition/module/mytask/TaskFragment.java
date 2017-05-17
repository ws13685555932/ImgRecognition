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

public class TaskFragment extends Fragment {

    @BindView(R.id.lv_tasks)
    ListView lvTasks;
    Unbinder unbinder;
    private List<TaskBean> taskList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        unbinder = ButterKnife.bind(this, view);
        String[] titles = new String[]{"签到", "标签达人", "绑定邮箱", "完善个人信息"};
        String[] dess = new String[]{"签到获得1积分，累计签到获得更多", "成功贴5个标签获得5积分"
                , "新用户绑定邮箱获得5积分", "新用户完善个人信息获得5积分"};
        int[] progress = new int[]{0, 3, 0, 1};
        int[] totals = new int[]{1, 5, 1, 1};

        for (int i = 0; i < 4; i++) {
            TaskBean task = new TaskBean();
            task.setTitle(titles[i]);
            task.setDes(dess[i]);
            task.setTotal(totals[i]);
            task.setProgress(progress[i]);
            taskList.add(task);
        }
        View head = LayoutInflater.from(getContext()).inflate(R.layout.header_list,null);
        TextView tvLabel = (TextView) head.findViewById(R.id.label);
        tvLabel.setText("做任务，升等级");
        lvTasks.addHeaderView(head);
        lvTasks.setAdapter(new TaskAdapter(getContext(),taskList));
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
