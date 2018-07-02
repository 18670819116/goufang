package com.ljcs.cxwl.data.api.support;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 作者：hu on 2017/7/7
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class ErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        throwable.printStackTrace();
    }
}
