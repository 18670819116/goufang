package com.ljcs.cxwl.callback;

import com.qiniu.android.http.ResponseInfo;

/**
 * @author xlei
 * @Date 2018/7/3.
 */


public interface UploadCallback {
    void sucess(String url);

    void fail(String key, ResponseInfo info);
}
