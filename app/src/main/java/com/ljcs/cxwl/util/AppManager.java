package com.ljcs.cxwl.util;

import android.app.Activity;

import java.util.Stack;

/**
 * @author xlei
 * @Date 2018/7/2.
 */

public class AppManager {


    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private AppManager() {
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void finishTopActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束所有Activity除了指定Activity外
     */
    public void finishAllActivityWithoutThis() {
        Activity act = activityStack.lastElement();
        for (int i = (activityStack.size() - 1); i >= 0; i--) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        activityStack.add(act);
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {

        Stack<Activity> tempActivityStack = new Stack<>();
        for (int i = (activityStack.size() - 1); i >= 0; i--) {
            if (activityStack.get(i).getClass().equals(cls)) {
                tempActivityStack.add(activityStack.get(i));
                break;
            }
        }
        if (!tempActivityStack.isEmpty()) {
            finishActivity(tempActivityStack.get(0));
        }
    }

    /**
     * 结束所有Activity
     */
    @SuppressWarnings("WeakerAccess")
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

        } catch (Exception e) {
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 得到指定类名的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }
}
