package com.momo.imgrecognition.module.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.momo.imgrecognition.R;
import com.momo.imgrecognition.config.Config;
import com.momo.imgrecognition.utils.ShowUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.grid_gallery)
    GridView gridGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        List<File> fileList = new ArrayList<>();
        File picFileDir = new File(Config.DOWNLOAD_IMG_PATH);
        File flist[] = picFileDir.listFiles();
        if (flist == null || flist.length == 0) {
            ShowUtil.toast("您还未下载任何图片");
        }
        for (File file : flist) {
            fileList.add(file);
        }
        gridGallery.setAdapter(new GalleryAdapter(this,fileList));
    }
}
