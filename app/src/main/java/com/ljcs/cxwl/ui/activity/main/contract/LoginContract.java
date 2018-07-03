package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for LoginActivity
 * @Description: $description
 * @date 2017/10/17 08:38:03
 */
public interface LoginContract {
    interface View extends BaseView<LoginContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void loginSuccess(RegisterBean appLogin);
    }

    interface LoginContractPresenter extends BasePresenter {

        void login(Map<String, String> map);
    }
}