package com.ljcs.cxwl.data.api;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/14.
 */

public interface API {
    /**
     * 本地专用 本地120.25.79.232   120.24.163.177
     */
    String Periphery = "http://192.168.8.21:8080/cxwy_consumer_terminal";
    String uploadImage = "http://192.168.8.22:8080/wygl/mall/upload_uploadAndroidFile";


 // String IP_PRODUCT = "http://192.168.8.20:8080/wygl";
    String BASE_URL_2 = "http://192.168.8.20:8080/";
    String IP_XUNGENG = "http://xungeng.hnchxwl.com/";     //
    String IP_PRODUCT = "http://120.25.79.232/wygl";
    String BASE_URL = IP_PRODUCT + "/";
    long CONNECT_TIMEOUT = 30 * 1000;

    long IO_TIMEOUT = 60 * 1000;
    String URL_GET_ALL_LOGIN = "xzs/xzs_login";//登陆
    //主机列表
    String ZHUJI_LIEBIAO = "/wygl/paian/xinzhushou_findPaianZhujiList";
}
