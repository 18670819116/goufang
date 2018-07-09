package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for SuggestActivity
 * @Description: $description
 * @date 2018/07/06 15:46:33
 */
public interface SuggestContract {
    interface View extends BaseView<SuggestContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface SuggestContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}