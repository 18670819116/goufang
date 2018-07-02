package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for MatesInfoFourActivity
 * @Description: $description
 * @date 2018/06/27 16:35:11
 */
public interface MatesInfoFourContract {
    interface View extends BaseView<MatesInfoFourContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MatesInfoFourContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}