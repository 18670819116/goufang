package com.ljcs.cxwl.util;

import com.ljcs.cxwl.callback.UploadCallback;
import com.orhanobut.logger.Logger;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/3.
 */

public class QiniuUploadUtil {
    public static void initQiniu() {
        Configuration config = new Configuration.Builder().chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                //.zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
// 重用uploadManager一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config);
    }

    public static void uploadPic(final String path, String _uploadToken, final UploadCallback callBack) {

        UploadManager uploadManager = new UploadManager();
        final String curUrl = "android_zjw/" + System.currentTimeMillis();
        //put第二个参数设置文件名
        Logger.i("开始上传单张图--------" + curUrl);
        uploadManager.put(path, curUrl, _uploadToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()) {
                    //// TODO: 2018/7/5 y以后优化
//                    File file = new File(path);
//                    if (file.exists()){
//                        file.delete();
//                        Logger.i("文件删除成功");
//                    }
                    Logger.i("单图上传成功");
                    callBack.sucess(curUrl);
                } else {
                    Logger.e("单图上传失败" + info.toString());
                    callBack.fail(key, info);
                }
            }
        }, null);

    }

    private static int curUploadImgIndex = 0;

    public static void uploadPics(final List<String> list, String _uploadToken, final UploadCallback callBack) {
        UploadManager uploadManager = new UploadManager();
        curUploadImgIndex = 0;
        final List<String> urlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String curUrl = "android_zjw/" + System.currentTimeMillis();
            //put第二个参数设置文件名
            urlList.add(curUrl);
            Logger.i("开始传第" + i + "张图--------" + curUrl);
            final int finalI = i;
            uploadManager.put(list.get(i), curUrl, _uploadToken, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject response) {
                    curUploadImgIndex++;
                    if (info.isOK()) {
                        //// TODO: 2018/7/5 y以后优化
//                        File file = new File(list.get(finalI));
//                        if(file.exists()){
//                            file.delete();
//                            Logger.i("文件删除成功");
//                        }
                        if ((curUploadImgIndex) == list.size()) {
                            Logger.i("多图上传成功");
                            callBack.sucess(urlList);
                        }
                    } else {
                        Logger.e("多图上传失败" + info.toString());
                        callBack.fail(key, info);
                    }
                }
            }, null);

        }
    }


}
