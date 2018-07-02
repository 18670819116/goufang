package com.ljcs.cxwl.data.api;

/**
 * @author zhaoyun
 * @desc 功能描述
 * @date 2016/7/29 18:21
 */
public class ChuangxinHttpException extends Exception {

    private long mUserCode;
    private String mRetCode;
    private String mRetMsg;

    public ChuangxinHttpException(long userCode , String retCode , String retMsg){
        super("HitaoHttpException => userCode: " + userCode + ",retCode: " + retCode + ",retMsg: " + retMsg);
        mUserCode = userCode;
        mRetCode = retCode;
        mRetMsg = retMsg;
    }

    public ChuangxinHttpException(){
        super("HitaoHttpException => response is null");
    }

    public long getUserCode() {
        return mUserCode;
    }

    public String getRetCode() {
        return mRetCode;
    }

    public String getRetMsg() {
        return mRetMsg;
    }

}
