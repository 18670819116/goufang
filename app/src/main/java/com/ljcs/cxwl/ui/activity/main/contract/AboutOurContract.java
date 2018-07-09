package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for AboutOurActivity
 * @Description: $description
 * @date 2018/07/09 11:07:19
 */
public interface AboutOurContract {
    interface View extends BaseView<AboutOurContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface AboutOurContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}