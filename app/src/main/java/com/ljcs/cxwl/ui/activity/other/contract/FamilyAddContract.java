package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for FamilyAddActivity
 * @Description: $description
 * @date 2018/07/02 10:34:10
 */
public interface FamilyAddContract {
    interface View extends BaseView<FamilyAddContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface FamilyAddContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}