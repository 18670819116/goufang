package com.ljcs.cxwl.data.api;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/14.
 */

public interface API {
//    String PIC = "http://218.77.54.125:11000/img?path=";
//    String IP_PRODUCT = "http://218.77.54.125:11001/api";
//    String IP_PRODUCT1 = "http://218.77.54.125:11000";

    String PIC = "http://app.cszjw.net:11000/img?path=";
    String IP_PRODUCT = "http://app.cszjw.net:11001/api";
    String IP_PRODUCT1 = "http://app.cszjw.net:11000";
// String PIC = "http://192.168.8.130/img?path=";
//    String IP_PRODUCT = "http://192.168.8.130/api";
//    String IP_PRODUCT1 = "http://192.168.8.130";


    String URL_ZJW_INDEX = "http://szjw.changsha.gov.cn/wap";//住建委网站链接

    String BASE_URL = IP_PRODUCT + "/";
    long CONNECT_TIMEOUT = 30 * 1000;
    long IO_TIMEOUT = 60 * 1000;
    String URL_POST_LOGIN = "login/zjw/user/login";//登陆
    String URL_POST_GET_CODE = "login/back/code";//找回密码验证码
    String URL_POST_GET_REGISTER_CODE = "login/registe/code";//注册验证码
    String URL_POST_GET_CHANGE_CODE = "login/updsj/code";//修改手机号码验证码
    String URL_POST_REGISTER = "login/zjw/user/register";//注册
    String URL_POST_FORGETPWD = "login/zjw/user/back";//忘记密码
    String URL_POST_LOGINOUT = "login/zjw/user/logout";//退出登录
    String URL_POST_CHANGEPWD = "login/zjw/user/update";//原密码修改密码
    String URL_GET_UPDATA_VERSION = "login/zjw/user/getversions";//更新

    String URL_POST_ALL_INFO = "family/zjw/user/allmessage";//获取所有的家庭信息；
    String URL_POST_CERINFO = "family/zjw/user/verify";//实名认证提交信息
    String URL_POST_CERINFO_LAST = "certification/zjw/user/modifystatus";//实名认证最后一步提交信息
    String URL_GET_CERINFO_DETAIL = "certification/zjw/user/verifydetail";//获取实名认证信息详情


    String URL_POST_MATESINFO = "family/zjw/user/savepoxx";//添加和修改配偶信息
    String URL_POST_MATESINFO_ZINV = "family/zjw/user/saveznxx";//添加和修改家庭成员 子女
    String URL_POST_MATESINFO_DELETE = "family/zjw/user/delznxx";//删除家庭成员 子女
    String URL_POST_MATESINFO_COMMIT = "family/zjw/user/submitgfsq";//提交信息
    String URL_POST_MATESINFO_SAVE = "family/jzw/user/applyjtcy";//保存家庭成员信息

    String URL_POST_HUKOU = "family/zjw/user/saveself";//添加/修改户籍信息
    String URL_GET_QINIU_TOKEN = "http://wy.iot.xin/qiniu/qiniu_getQiniuToken.action";//获取七牛token
    String URL_GET_UPLOADFILE = IP_PRODUCT1 + "/upload";//ftp文件上传


    String URL_POST_ABOUTME = "login/zjw/user/abountme";//关于我们
    String URL_POST_COMMIT_SUGGEST = "login/zjw/user/addfeedback";//提交意见
    String URL_POST_COMMIT_SHSUGGEST = "login/zjw/user/addappeals";//提交审核意见
    String URL_POST_SCAN = "login/zjw/scan/save";//提交二维码扫描信息
    String URL_POST_IS_SCAN = "login/zjw/scan/kprg";//查询是否提交二维码扫描信息
    String URL_POST_CHANGE_PHONE = "login/zjw/user/updsjhm";//修改手机号码

}
