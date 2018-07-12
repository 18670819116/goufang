package com.ljcs.cxwl.util;

import com.ljcs.cxwl.callback.UploadCallback;
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.data.api.HttpAPIWrapper;
import com.ljcs.cxwl.entity.CommonBean;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author xlei
 * @Date 2018/7/11.
 */

public class UploadUtil {
    private static int curUploadImgokhttp = 0;
    private static List<String> urlList = new ArrayList<>();

    public static void uploadPicsOkhttp(final HttpAPIWrapper httpAPIWrapper, final List<String> list, final
    UploadFileCallBack callBack) {

        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File(list.get
                (curUploadImgokhttp)));
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadFile", new File(list.get
                (curUploadImgokhttp)).getName(), fileRequestBody);
        Disposable subscribe = httpAPIWrapper.uploadFile(body).subscribe(new Consumer<CommonBean>() {
            @Override
            public void accept(@NonNull CommonBean commonBean) throws Exception {
                if (commonBean.code == Contains.REQUEST_SUCCESS) {
                    Logger.i("tag curUploadImgokhttp" + curUploadImgokhttp + "");
                    curUploadImgokhttp++;
                    urlList.add(commonBean.getData());
                    Logger.i("tag commonBean.getData()" + commonBean.getData() + "");
                    if (curUploadImgokhttp == list.size()) {
                        curUploadImgokhttp = 0;
                        callBack.sucess(urlList);
                        urlList.clear();
                    } else {
                        uploadPicsOkhttp(httpAPIWrapper, list, callBack);
                    }
                } else {
                    urlList.clear();
                    curUploadImgokhttp = 0;
                    callBack.fail(commonBean.msg);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                callBack.fail(throwable.toString());
                curUploadImgokhttp = 0;
                urlList.clear();
            }
        });
    }
}
