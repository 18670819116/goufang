package com.ljcs.cxwl.ui.activity.changephone.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ChangePhoneThirdActivity
 * @Description: $description
 * @date 2018/07/09 16:39:43
 */
public interface ChangePhoneThirdContract {
    interface View extends BaseView<ChangePhoneThirdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ChangePhoneThirdContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}