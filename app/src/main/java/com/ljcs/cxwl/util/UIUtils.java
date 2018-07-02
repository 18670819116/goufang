package com.ljcs.cxwl.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ljcs.cxwl.application.AppConfig;

import java.util.ArrayList;
import java.util.List;


public class UIUtils {

    public static Context getContext() {
        return AppConfig.getInstance();
    }


    // /////////////////加载资源文件 ///////////////////////////

    // 获取字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    // 获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    // 获取图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    // 获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //根据id获取颜色的状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    // 获取尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);// 返回具体像素值
    }

    // /////////////////dip和px转换//////////////////////////

    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // /////////////////加载布局文件//////////////////////////
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static int getDisplayWidth(Activity context) {
        WindowManager wm = context.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getDisplayHeigh(Activity context) {
        WindowManager wm = context.getWindowManager();
        int heigh = wm.getDefaultDisplay().getHeight();
        return heigh;
    }

    public static int widthDesignPx2RealPx(Activity context, float designPx) {
        int realWidth = getDisplayWidth(context);

        return (int) (realWidth / 1080 * designPx);
    }


    public static int widthDesignPx2RealPx(Activity context, int designPx) {
        int realWidth = getDisplayWidth(context);

        return realWidth / 1080 * designPx;
    }

    public static int heightDesignPx2RealPx(Activity context, int designPx) {
        int height = getDisplayHeigh(context);

        return height / 920 * designPx;
    }


    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    /**
     * 配置recycleview
     *
     * @param recyclerView
     * @param layoutManager
     */
    public static void configRecycleView(final RecyclerView recyclerView
            , RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static void configSwipeRefreshLayoutColors(SwipeRefreshLayout layout) {
        layout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public static View getRecyclerBottomView(Context context) {
        return getRecyclerBottomView(context, 34);
    }

    public static View getRecyclerBottomView(Context context, int height) {
        View view = new View(context);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        view.setLayoutParams(layoutParams);
        return view;
    }

    public static View getRecyclerTopView(Context context, int height) {
        View view = new View(context);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        view.setLayoutParams(layoutParams);
        return view;
    }

    public static List<String> getPhoneAndName(Intent data, Context context) {
        List<String> list = new ArrayList<>();
        if (data != null) { //判断返回的intent是不是为空
            Uri uri = data.getData();
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            String strNumber = "";
            String strName = "";
            if (cursor != null && cursor.moveToNext()) {
                //拿到id
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //根据id查找电话
                Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                //有可能有多个号码，选择第一个，这里有点粗暴了
                if (phone.moveToFirst()) {
                    strNumber = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    strNumber = strNumber.replace(" ", "").replace("-", "");
                    strName = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //记得释放资源
                    phone.close();
                    cursor.close();

                } else {
                    //记得释放资源
                    try {
                        phone.close();
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            list.add(strName);
            list.add(strNumber);

        }
        return list;
    }
}
