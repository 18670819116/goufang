package com.ljcs.cxwl.ui.activity.home.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for HomeActivity
 * @Description: $description
 * @date 2018/07/27 14:45:35
 */
public interface HomeContract {
    interface View extends BaseView<HomeContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface HomeContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}