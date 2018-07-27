package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for MatesInfoFiveActivity
 * @Description: $description
 * @date 2018/06/28 12:16:34
 */
public interface MatesInfoFiveContract {
    interface View extends BaseView<MatesInfoFiveContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MatesInfoFiveContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}