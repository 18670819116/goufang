package com.ljcs.cxwl.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.vondear.rxtool.RxTool;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * @author xlei
 * @ClassName: AppConfig
 * @Description: Application 对象
 */

public class AppConfig extends Application {
    /**
     * 为了实现每次使用该类时不创建新的对象而创建的静�?对象
     */
    public static AppConfig instance;
    private AppComponent mAppComponent;

    public AppConfig() {

    }

    public static synchronized AppConfig getInstance() {
        if (null == instance) {
            instance = new AppConfig();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupApplicationComponent();
        instance = this;
        //滑动返回
        BGASwipeBackHelper.init(this, null);
        RxTool.init(this);
        //bugly
        CrashReport.initCrashReport(this, "cada4dc780", false);
        //扫码
        ZXingLibrary.initDisplayOpinion(this);
        //阿里云推送
        initCloudChannel(this);
        //友盟
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(false);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    protected void setupApplicationComponent() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).aPIModule(new APIModule(this))
                .build();
        mAppComponent.inject(this);

    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Logger.i("阿里云推送初始化成功  response" + response);
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Logger.e("阿里云推送初始化失败-- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
