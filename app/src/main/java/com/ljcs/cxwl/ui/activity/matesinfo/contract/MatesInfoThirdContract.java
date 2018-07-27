package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

/**
 * @author xlei
 * @Package The contract for MatesInfoThirdActivity
 * @Description: $description
 * @date 2018/06/27 16:33:53
 */
public interface MatesInfoThirdContract {
    interface View extends BaseView<MatesInfoThirdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MatesInfoThirdContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}