package com.ljcs.cxwl.callback;

import com.qiniu.android.http.ResponseInfo;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/3.
 */


public interface UploadCallback {
    /**
     * 单图上传返回上传照片路劲
     * @param url
     */
    void sucess(String url);

    /**
     * 单图上传返回上传照片路劲
     * @param url
     */
    void sucess(List<String> url);

    void fail(String key, ResponseInfo info);
}
