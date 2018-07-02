package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for FamilyRegisterActivity
 * @Description: $description
 * @date 2018/06/27 17:51:07
 */
public interface FamilyRegisterContract {
    interface View extends BaseView<FamilyRegisterContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface FamilyRegisterContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}