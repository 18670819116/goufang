package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for MatesInfoOneActivity
 * @Description: $description
 * @date 2018/06/27 16:32:06
 */
public interface MatesInfoOneContract {
    interface View extends BaseView<MatesInfoOneContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface MatesInfoOneContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}