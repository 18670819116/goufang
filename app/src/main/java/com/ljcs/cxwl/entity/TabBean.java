package com.ljcs.cxwl.entity;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public class TabBean {
    private String title;
    private int  resid;
    private Class   fragment;

    public String getTitle() {
        return title;
    }


    public TabBean(String title, int resid, Class fragment) {
        this.title = title;
        this.resid = resid;
        this.fragment = fragment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
