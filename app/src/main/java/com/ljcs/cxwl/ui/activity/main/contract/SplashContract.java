package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for SplashActivity
 * @Description: $description
 * @date 2018/06/26 08:44:55
 */
public interface SplashContract {
    interface View extends BaseView<SplashContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        /**
         * 登录成功，跳转到主页面
         */
        void loginSuccees();

        /**
         * 跳转到登录界面
         */
        void jumpToLogin();

        /**
         * 跳转到欢迎页面
         */
        void jumpToGuest();
    }

    interface SplashContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        /**
         * 查询是否已经记录了用户信息
         */
        void queryShipperInfo();

        /**
         * 登录请求
         * @param map
         */
        void login(Map map);

        void updataApp();
    }
}