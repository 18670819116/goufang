package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for AboutCertificationActivity
 * @Description: $description
 * @date 2018/07/10 10:26:14
 */
public interface AboutCertificationContract {
    interface View extends BaseView<AboutCertificationContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface AboutCertificationContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}