package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ComplainActivity
 * @Description: $description
 * @date 2018/07/09 10:58:14
 */
public interface ComplainContract {
    interface View extends BaseView<ComplainContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ComplainContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}