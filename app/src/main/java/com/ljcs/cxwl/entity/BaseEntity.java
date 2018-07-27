package com.ljcs.cxwl.entity;

public class BaseEntity {

    /**
     * success : true
     * code : 200
     * msg : 操作成功
     * error : null
     * data : 888888
     * rows : 888888
     * total : null
     * token : null
     */

    public boolean success;
    public int code;
    public String msg;
    public String error;
    public int total;
    public String token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
