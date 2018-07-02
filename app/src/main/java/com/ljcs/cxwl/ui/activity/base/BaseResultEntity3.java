package com.ljcs.cxwl.ui.activity.base;

/**
 * 回调信息统一封装类
 * 作者：yishangfei on 2016/12/31 0031 10:16
 * 邮箱：yishangfei@foxmail.com
 */
public class BaseResultEntity3<T> {
    //    提示信息
    private String MSG;
    //  判断标示
    private int status;

    //显示数据（用户需要关心的数据）
    private T row;

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getRow() {
        return row;
    }

    public void setRow(T row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "BaseResultEntity{" +
                "MSG='" + MSG + '\'' +
                ", status=" + status +
                ", row=" + row +
                '}';
    }
}
