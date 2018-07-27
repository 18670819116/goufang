package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for CertificationOneActivity
 * @Description: $description
 * @date 2018/06/26 19:26:21
 */
public interface CertificationOneContract {
    interface View extends BaseView<CertificationOneContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CertificationOneContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}