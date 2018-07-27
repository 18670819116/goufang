package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.ScanBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterStatusActivity
 * @Description: $description
 * @date 2018/07/05 10:00:42
 */
public interface FamilyRegisterStatusContract {
    interface View extends BaseView<FamilyRegisterStatusContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void allInfoSuccess(AllInfo baseEntity);

        void intiViews();

        void isScanSuccess(ScanBean baseEntity);

    }

    interface FamilyRegisterStatusContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void allInfo(Map map);

        void isScan();
    }
}