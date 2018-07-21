package com.ljcs.cxwl.ui.activity.web.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for WebSatisficingActivity
 * @Description: $description
 * @date 2018/07/12 11:56:05
 */
public interface WebSatisficingContract {
    interface View extends BaseView<WebSatisficingContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface WebSatisficingContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}