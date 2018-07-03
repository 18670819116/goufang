package com.ljcs.cxwl.data.api;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/14.
 */

public interface API {

    String IP_PRODUCT = "http://192.168.8.130/api";
    String BASE_URL = IP_PRODUCT + "/";
    long CONNECT_TIMEOUT = 30 * 1000;
    long IO_TIMEOUT = 60 * 1000;
    String URL_POST_LOGIN = "login/zjw/user/login";//登陆
    String URL_POST_GET_CODE = "login/zjw/user/code";//验证码
    String URL_POST_REGISTER = "login/zjw/user/register";//注册
    String URL_POST_FORGETPWD = "login/zjw/user/back";//忘记密码
    String URL_POST_LOGINOUT = "login/zjw/user/logout";//退出登录
    String URL_POST_CHANGEPWD = "login/zjw/user/update";//原密码修改密码
}
