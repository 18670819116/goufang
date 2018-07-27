package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for FamilyAddNowActivity
 * @Description: $description
 * @date 2018/07/03 11:09:00
 */
public interface FamilyAddNowContract {
    interface View extends BaseView<FamilyAddNowContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface FamilyAddNowContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}