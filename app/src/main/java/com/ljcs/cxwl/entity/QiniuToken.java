package com.ljcs.cxwl.entity;


/**
 * 作者：yishangfei on 2017/1/9 0009 16:54
 * 邮箱：yishangfei@foxmail.com
 */
public class QiniuToken extends BaseEntity {
    /**
     * uptoken : 5kjpxZ5oh0gc3k10Jxf7gUmXg646j04Ywl27BjC9:QNyMXPtfWVlPfbULcpKUMOhK8L8
     * =:eyJzY29wZSI6InlheGluIiwiZGVhZGxpbmUiOjE0ODM5NTQ5Mjd9
     */

    private String uptoken;

    public String getUptoken() {
        return uptoken;
    }

    public void setUptoken(String uptoken) {
        this.uptoken = uptoken;
    }
}
