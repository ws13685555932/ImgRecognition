package com.momo.imgrecognition.module.changeskin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.module.changeskin.adapter.SkinListAdapter;
import com.momo.imgrecognition.module.changeskin.bean.SkinItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeSkinActivity extends AppCompatActivity {

    List<SkinItem> skinList;

    @BindView(R.id.lv_skin_list)
    ListView lvSkinList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
        ButterKnife.bind(this);

        initData();

        lvSkinList.setAdapter(new SkinListAdapter(this,skinList));
    }

    private void initData() {
        skinList  = new ArrayList<>();
        String[] skinNameArr = getResources().getStringArray(R.array.skin_name_list);
        for(int i=0;i<skinNameArr.length;i++) {
            SkinItem item = new SkinItem();
            item.setIconDrawable(R.drawable.ic_skin_item);
            item.setCurrUseDrawable(R.drawable.ic_tick);
            item.setSkinText(skinNameArr[i]);
            skinList.add(item);
        }
    }
}
