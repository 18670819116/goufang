package com.ljcs.cxwl.data.api;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/14.
 */

public interface API {
    String PIC = "http://img0.hnchxwl.com/";
//    String IP_PRODUCT = "http://192.168.8.25/api";
    String IP_PRODUCT = "http://218.77.54.125:11001/api";

    String BASE_URL = IP_PRODUCT + "/";
    long CONNECT_TIMEOUT = 30 * 1000;
    long IO_TIMEOUT = 60 * 1000;
    String URL_POST_LOGIN = "login/zjw/user/login";//登陆
    String URL_POST_GET_CODE = "login/zjw/user/code";//验证码
    String URL_POST_REGISTER = "login/zjw/user/register";//注册
    String URL_POST_FORGETPWD = "login/zjw/user/back";//忘记密码
    String URL_POST_LOGINOUT = "login/zjw/user/logout";//退出登录
    String URL_POST_CHANGEPWD = "login/zjw/user/update";//原密码修改密码
    String URL_GET_UPDATA_VERSION = "login/zjw/user/getversions";//更新

    String URL_POST_ALL_INFO = "family/jzw/user/applyMsg";//获取所有的家庭信息；
    String URL_POST_CERINFO = "certification/zjw/user/verify";//实名认证提交信息
    String URL_POST_CERINFO_LAST = "certification/zjw/user/modifystatus";//实名认证最后一步提交信息
    String URL_GET_CERINFO_DETAIL = "certification/zjw/user/verifydetail";//获取实名认证信息详情


    String URL_POST_MATESINFO = "family/jzw/user/savepoxx";//添加和修改配偶信息
    String URL_POST_MATESINFO_ZINV = "family/jzw/user/savejtcy";//添加和修改家庭成员 子女
    String URL_POST_MATESINFO_DELETE = "family/jzw/user/deletejtcy";//删除家庭成员 子女
    String URL_POST_MATESINFO_COMMIT = "family/jzw/user/apply";//提交信息
    String URL_POST_MATESINFO_SAVE = "family/jzw/user/applyjtcy";//保存家庭成员信息

    String URL_POST_HUKOU = "family/jzw/user/addregistered";//添加/修改户籍信息
    String URL_GET_QINIU_TOKEN = "http://wy.iot.xin/qiniu/qiniu_getQiniuToken.action";//获取七牛token



}
