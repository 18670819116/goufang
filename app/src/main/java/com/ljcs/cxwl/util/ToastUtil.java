package com.ljcs.cxwl.util;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;


import com.ljcs.cxwl.application.AppConfig;

import javax.inject.Singleton;

/**
 * 提示工具类
 *
 * @author lijing
 */
@Singleton
public class ToastUtil {

    private static Toast longToast;
    private static Toast shortToast;
    private Toast customDurationToast;
    private static Toast toast;
    private static Runnable run = new Runnable() {
        public void run() {
            toast.cancel();
        }
    };


    public Toast displayLongToast(final String text) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (longToast != null) {
                    longToast.cancel();
                }
                longToast = Toast.makeText(AppConfig.instance, text, Toast.LENGTH_LONG);
                longToast.show();
                Looper.loop();
            }
        }.start();
        return longToast;
    }

    public static Toast displayShortToast(final String text) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (shortToast != null) {
                    shortToast.cancel();
                }
                shortToast = Toast.makeText(AppConfig.instance, text, Toast.LENGTH_SHORT);
                shortToast.show();
                Looper.loop();
            }
        }.start();
        return shortToast;
    }

    public Toast displayCustomDurationToast(final String text, final int duration) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (customDurationToast != null) {
                    customDurationToast.cancel();
                }
                customDurationToast = Toast.makeText(AppConfig.instance, text, duration);
                customDurationToast.show();
                Looper.loop();
            }
        }.start();
        return customDurationToast;
    }

    public static void showShort(String info) {
        if (toast == null) {
            toast = Toast.makeText(AppConfig.getInstance(), info, Toast.LENGTH_SHORT);
        } else {
            toast.setText(info);
        }
        toast.show();
    }

    public static void show(Context context, String info) {
        if (toast != null) {
            toast.cancel();
            toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void showLong(String info) {
        if (toast == null) toast = Toast.makeText(AppConfig.instance, info, Toast.LENGTH_LONG);
        else toast.setText(info);
        toast.show();
    }

    public static void showCenterShort(String info) {
        if (toast == null) {
            toast = Toast.makeText(AppConfig.instance, info, Toast.LENGTH_SHORT);
        } else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
//    public static void
}
