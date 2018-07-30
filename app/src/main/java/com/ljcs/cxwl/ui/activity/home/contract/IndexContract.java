package com.ljcs.cxwl.ui.activity.home.contract;

import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;
/**
 * @author xlei
 * @Package The contract for IndexFragment
 * @Description: $description
 * @date 2018/07/27 15:03:41
 */
public interface IndexContract {
    interface View extends BaseView<IndexContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
    }

    interface IndexContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
    }
}