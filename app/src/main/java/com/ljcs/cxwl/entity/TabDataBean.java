package com.ljcs.cxwl.entity;

/**
 * 作者：hu on 2017/7/19
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class TabDataBean {
    private int tabName;
    private int tabIcon;
    private Class content; //对应的内容类

    public TabDataBean(int tabName, int tabIcon, Class content) {
        this.tabName = tabName;
        this.tabIcon = tabIcon;
        this.content = content;
    }

    public int getTabName() {
        return tabName;
    }

    public void setTabName(int tabName) {
        this.tabName = tabName;
    }

    public int getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(int tabIcon) {
        this.tabIcon = tabIcon;
    }

    public Class getContent() {
        return content;
    }

    public void setContent(Class content) {
        this.content = content;
    }
}