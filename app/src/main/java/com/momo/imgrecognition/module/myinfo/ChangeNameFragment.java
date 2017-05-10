package com.momo.imgrecognition.module.myinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momo.imgrecognition.R;

/**
 * Created by Administrator on 2017/5/10.
 */

public class ChangeNameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_change_name,container,false);
        return contentView;
    }
}
