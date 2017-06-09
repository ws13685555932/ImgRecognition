package com.momo.imgrecognition.module.search;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_extend)
    LinearLayout llExtend;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");
        list.add("android");

        lvSearch.setAdapter(new SearchAdpter(this, list));
        recycle.setAdapter(new RecyclerView.Adapter() {

            class ViewHolder extends RecyclerView.ViewHolder {

                CardView cardView;

                public ViewHolder(View itemView) {
                    super(itemView);
                    cardView = (CardView) itemView;
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_recycle_search_result, parent, false);

                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });

        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(grid);

        llExtend.post(new Runnable() {
            @Override
            public void run() {
                Log.d("mytag", "post(Runnable) : v_view1.getWidth():" + llExtend.getWidth()
                        + "  v_view1.getHeight():" + llExtend.getHeight());
            }
        });

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                llExtend.setVisibility(View.GONE);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.iv_back, R.id.et_search, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.et_search:
                llExtend.setVisibility(View.VISIBLE);
//                llSearch.setBackgroundColor(getResources().getColor(R.color.white));
//                ivSearch.setBackgroundTintList(getResources().getColorStateList(R.color.gray_dark));
//                ivBack.setBackgroundTintList(getResources().getColorStateList(R.color.gray_dark));
//                etSearch.setHintTextColor(getResources().getColorStateList(R.color.gray_dark));

                break;
            case R.id.iv_search:
                llExtend.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (llExtend.getVisibility() == View.VISIBLE) {
            llExtend.setVisibility(View.GONE);
        } else {
            finish();
        }
    }
}
