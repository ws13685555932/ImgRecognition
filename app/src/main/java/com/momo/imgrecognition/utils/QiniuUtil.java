package com.momo.imgrecognition.utils;

import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.utils.UrlSafeBase64;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.momo.imgrecognition.utils.ShowUtil.print;

/**
 * Created by Administrator on 2017/5/24.
 */

public class QiniuUtil {
    //七牛后台的key
    private static String AccessKey = "QX_NRcyn7o0htLCNF64xDaw1H8vmVGkHDYBlGTQ8";
    //七牛后台的secret
    private static String SecretKey = "7OndAcl1pgr0JWlWRM8y1gat8imZPL4KRQToLCJC";
    private static String BucketName = "test-demo";

    private static String domain = "http://oq543v9g0.bkt.clouddn.com/";

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    private static Configuration config = new Configuration.Builder()
            .zone(Zone.httpAutoZone)
            .build();
    private static UploadManager uploadManager = new UploadManager(config);

    public static String getToken(){
        JSONObject json = new JSONObject();
        long deadline = System.currentTimeMillis() / 1000 + 3600;
        try {
            json.put("deadline", deadline);// 有效时间为一个小时
            json.put("scope", BucketName);//存储空间的名字
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String encodedPutPolicy = UrlSafeBase64.encodeToString(json
                .toString().getBytes());
        byte[] sign = new byte[0];

        try {
            sign = HmacSHA1Encrypt(encodedPutPolicy, SecretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String encodedSign = UrlSafeBase64.encodeToString(sign);
        String uploadToken = AccessKey + ':' + encodedSign + ':'
                + encodedPutPolicy;
        return uploadToken;
    }

    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKeySpec secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }

    public static void uploadFile(final String filePath,final UploadListener uploadListener){
        if (filePath == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (uploadManager == null) {
                    uploadManager = new UploadManager();
                }
                String qiniuToken = getToken();
                uploadManager.put(filePath, null, qiniuToken,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo respInfo,
                                                 JSONObject jsonData) {

                                if (respInfo.isOK()) {
                                    print(jsonData.toString());
                                    String url = "";
                                    try {
                                        //图片的外链地址domain + key
                                        url = domain + jsonData.getString("key");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    uploadListener.onUploadSuccess(url);

                                } else {
                                    print(respInfo.error);
                                    uploadListener.onUploadFail(new Error("上传失败" + respInfo.error));
                                }
                            }



                        }, new UploadOptions(null, null, false,
                                new UpProgressHandler(){
                                    public void progress(String key, double percent){
                                        int progress  = (int) (percent * 100);
                                        ShowUtil.print("upload :" + progress + "%");
                                    }
                                }, null));
            }
        }).start();
    }

//    进度
//    new UploadOptions(null, null, false,
//                              new UpProgressHandler(){
//        public void progress(String key, double percent){
//            progressbar.setProgress((int) (percent * 100));
//        }
//    }, null)

    //上传回调
    public interface UploadListener {
        void onUploadSuccess(String url);

        void onUploadFail(Error error);
    }


}
