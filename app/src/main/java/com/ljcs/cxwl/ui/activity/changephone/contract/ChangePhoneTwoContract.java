package com.ljcs.cxwl.ui.activity.changephone.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ChangePhoneTwoActivity
 * @Description: $description
 * @date 2018/07/09 16:39:17
 */
public interface ChangePhoneTwoContract {
    interface View extends BaseView<ChangePhoneTwoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ChangePhoneTwoContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}