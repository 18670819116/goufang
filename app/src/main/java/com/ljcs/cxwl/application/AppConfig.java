package com.ljcs.cxwl.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.vondear.rxtools.RxTool;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * @author wwx
 * @ClassName: AppConfig
 * @Description: Application 对象
 * @date 2015�?1�?6�?下午1:36:10
 */

public class AppConfig extends Application {
    private AppComponent mAppComponent;
    /**
     * 为了实现每次使用该类时不创建新的对象而创建的静�?对象
     */
    public static AppConfig instance;
    public AppConfig() {

    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupApplicationComponent();
        instance=this;
        RxTool.init(this);
        //滑动返回
        BGASwipeBackHelper.init(this, null);
        //bugly
        CrashReport.initCrashReport(this, "cada4dc780", true);

    }

    public static synchronized AppConfig getInstance() {
        if (null == instance) {
            instance = new AppConfig();
        }
        return instance;
    }



    protected void setupApplicationComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .aPIModule(new APIModule(this))
                .build();
        mAppComponent.inject(this);

    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();


    }

}
