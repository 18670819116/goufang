package com.ljcs.cxwl.ui.activity.scan.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for ScanActivity
 * @Description: $description
 * @date 2018/07/13 11:32:30
 */
public interface ScanContract {
    interface View extends BaseView<ScanContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface ScanContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}