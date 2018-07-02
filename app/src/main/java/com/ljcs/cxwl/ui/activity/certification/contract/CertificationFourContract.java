package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for CertificationFourActivity
 * @Description: $description
 * @date 2018/06/26 19:28:19
 */
public interface CertificationFourContract {
    interface View extends BaseView<CertificationFourContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface CertificationFourContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}