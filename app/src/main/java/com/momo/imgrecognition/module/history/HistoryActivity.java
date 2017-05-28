package com.momo.imgrecognition.module.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.apiservice.ResponseInfo;
import com.momo.imgrecognition.apiservice.UserService;
import com.momo.imgrecognition.config.UserConfig;
import com.momo.imgrecognition.module.BaseActivity;
import com.momo.imgrecognition.module.login.bean.User;
import com.momo.imgrecognition.module.taglater.bean.TagLaterBean;
import com.momo.imgrecognition.utils.HttpManager;
import com.momo.imgrecognition.utils.HttpObserver;
import com.momo.imgrecognition.utils.RxSchedulersHelper;
import com.momo.imgrecognition.utils.SharedUtil;
import com.momo.imgrecognition.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class HistoryActivity extends BaseActivity {

    @BindView(R.id.lv_history_list)
    ListView lvHistoryList;

    private List<HistoryBean> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

//        getData();

        List<HistoryBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HistoryBean bean = new HistoryBean();
            bean.setAdminName("管理员：momo1005" + i);
            bean.setResId(R.drawable.bg_cover);
            bean.setTagStr("科学,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫,人文景观,动漫");
            bean.setTime(1494932883);
            list.add(bean);
        }

        lvHistoryList.setAdapter(new HistoryAdapter(this, list));

    }

    int page = 1;

    private void getData() {
        UserService userService = HttpManager.getInstance().createService(UserService.class);
        HistoryRequest request = new HistoryRequest();
        request.setId((String) SharedUtil.getParam(UserConfig.USER_ID, 0));
        request.setLimit(20);
        request.setPage(page);
        page++;
        Observable<ResponseInfo<HistoryResponse>> call = userService.getHistoryLabel(request);
        call.compose(RxSchedulersHelper.<ResponseInfo<HistoryResponse>>io_main())
                .subscribe(new HttpObserver<HistoryResponse>() {
                    @Override
                    public void onSuccess(HistoryResponse historyResponse) {
                        showHistory(historyResponse);
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
    }

    private void showHistory(HistoryResponse historyResponse) {
        List<HistoryResponse.LabelListBean> picList = historyResponse.getLabelList();

        for (int i = 0; i < picList.size(); i++) {
            HistoryBean bean = new HistoryBean();
            bean.setTime(TimeUtil.convertTimeStamp(picList.get(i).getCreated_time()));
            String tagStr = picList.get(i).getLabel();
            bean.setTags(Arrays.asList(tagStr.split(",")));
            bean.setPicId(picList.get(i).getPictureId());
//            bean.setPicUrl(picList.get(i).get);

        }
    }
}
