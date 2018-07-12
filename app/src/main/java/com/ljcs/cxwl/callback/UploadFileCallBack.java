package com.ljcs.cxwl.callback;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/11.
 */

public interface UploadFileCallBack {
    void sucess(List<String> url);

    void fail(String msg);
}
