package com.ljcs.cxwl.ui.activity.changephone.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ChangePhoneOneActivity
 * @Description: $description
 * @date 2018/07/09 16:38:08
 */
public interface ChangePhoneOneContract {
    interface View extends BaseView<ChangePhoneOneContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ChangePhoneOneContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}