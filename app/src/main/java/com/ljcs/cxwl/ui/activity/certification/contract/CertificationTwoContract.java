package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for CertificationTwoActivity
 * @Description: $description
 * @date 2018/06/26 19:26:58
 */
public interface CertificationTwoContract {
    interface View extends BaseView<CertificationTwoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CertificationTwoContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}