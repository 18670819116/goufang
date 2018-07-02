package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for QualificationExaminationActivity
 * @Description: $description
 * @date 2018/06/27 16:49:13
 */
public interface QualificationExaminationContract {
    interface View extends BaseView<QualificationExaminationContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface QualificationExaminationContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}