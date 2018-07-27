package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for CertificationThirdActivity
 * @Description: $description
 * @date 2018/06/26 19:27:38
 */
public interface CertificationThirdContract {
    interface View extends BaseView<CertificationThirdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CertificationThirdContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}