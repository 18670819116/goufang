package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyMatesStatusActivity
 * @Description: $description
 * @date 2018/07/05 13:56:02
 */
public interface FamilyMatesStatusContract {
    interface View extends BaseView<FamilyMatesStatusContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void allInfoSuccess(AllInfo baseEntity);
    }

    interface FamilyMatesStatusContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void allInfo(Map map);
    }
}