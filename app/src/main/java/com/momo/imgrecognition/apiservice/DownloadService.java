package com.momo.imgrecognition.apiservice;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/5/24.
 */

public interface DownloadService {

    @GET
    Observable<ResponseBody> downloadFileWithUrl(@Url String fileUrl);
}
