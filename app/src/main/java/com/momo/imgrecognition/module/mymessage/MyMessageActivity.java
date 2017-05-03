package com.momo.imgrecognition.module.mymessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.mymessage.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMessageActivity extends AppCompatActivity {

    @BindView(R.id.lv_my_message)
    ListView lvMyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        ButterKnife.bind(this);

        String[] titles = new String[]{
                "八重樱已加入《崩坏3》女武神豪华套餐！4月18日前登录即领300水晶！"
                , "2017国产动画春夏导视"
                , "2017春季新番导视"
                , "《FateGrandOrder》X《空之境界》联动开启！两仪式限时登场！"
        };

        String[] contents = new String[]{
                "舰长SAMA，《崩坏3》新版本樱色轮回已全面开启，女武神八重樱参见！新增开放世界冒险模式及联机模式！4月18日前登舰，即领300水晶！登舰入口http://acg.tv/u1S0"
                , "新一季国产动画新片陆续登场，还有更多大热连载剧集推荐！大家一起追起来吧ヽ( ^∀^)ﾉhttp://www.bilibili.com/blackboard/activity-S1fnF3s3e.html"
                , "埃罗芒阿老师、从零开始的魔法书、时钟机关之星、樱花任务、夏目友人帐第6季、火影忍者博人传、Re:CREATORS…快来补完你的追番清单吧！http://www.bilibili.com/blackboard/activity-S1aPZanjx.html"
                , "各位Master，3.13-3.28期间《Fate/Grand Order》X《空之境界》联动活动限时开启，参与活动即可获得两仪式(Assassin)！借助拥有“直死之魔眼”的少女的力量，对变异特异点展开调查的各位Master究竟能否发现真相呢？两仪式(Saber)也将限时登场！详情请见http://acg.tv/u1RK"
        };

        String[] dates = new String[]{
                "2017-04-13 17:43:24"
                , "2017-04-02 20:00:00"
                , "2017-04-01 18:00:00"
                , "2017-03-14 11:00:00"
        };

        List<MessageBean> messageList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MessageBean message = new MessageBean();
            message.setTitle(titles[i]);
            message.setContent(contents[i]);
            message.setDate(dates[i]);
            messageList.add(message);
        }

        lvMyMessage.setAdapter(new MessageAdapter(this,messageList));
    }
}
