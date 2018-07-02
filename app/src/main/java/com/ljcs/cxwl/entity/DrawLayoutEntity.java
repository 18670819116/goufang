package com.ljcs.cxwl.entity;

/**
 * @author xlei
 * @Date 2017/10/18.
 */

public class DrawLayoutEntity {
    private int imgResid;
    private String contentText;

    public DrawLayoutEntity(String contentText,int imgResid ) {
        this.imgResid = imgResid;
        this.contentText = contentText;
    }

    public int getImgResid() {
        return imgResid;
    }

    public void setImgResid(int imgResid) {
        this.imgResid = imgResid;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
