package com.momo.imgrecognition.module.myscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.myinfo.UserInfo;
import com.momo.imgrecognition.module.myinfo.UserRequest;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.ShowUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MyScoreActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_next_level)
    TextView tvNextLevel;

    private int[] scores = new int[]{
            25, 50, 150, 300, 600, 1500, 3000, 8000, 18000, 40000
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);
        ButterKnife.bind(this);

        getUserInfo();

    }

    private void getUserInfo() {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        UserRequest request = new UserRequest();
        request.setToken((String) SharedUtil.getParam(UserConfig.USER_TOKEN, ""));
        request.setId((Integer) SharedUtil.getParam(UserConfig.USER_ID, 0));
        request.setName((String) SharedUtil.getParam(UserConfig.USER_NAME, ""));
        Observable<ResponseInfo<UserInfo>> call = userService.getUserInfo(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<UserInfo>>io_main())
                .subscribe(new HttpObserver<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo userInfo) {
                        int level = userInfo.getLevel();
                        int currScore = userInfo.getScore();
                        tvScore.setText(currScore+"");
                        if(level == 10){
                            tvNextLevel.setText("恭喜您已满级！");
                        }else {
                            int lastScore = scores[level] - currScore;
                            tvNextLevel.setText("距离下一级还差"+lastScore+"分");
                        }
                    }

                    @Override
                    public void onFailed(String message) {
                        ShowUtil.print(message);
                    }
                });
    }


}
