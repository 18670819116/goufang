package com.ljcs.cxwl.ui.activity.scan.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

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

        void scanSuccess(BaseEntity baseEntity);
    }

    interface ScanContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void scan(Map map);
    }
}