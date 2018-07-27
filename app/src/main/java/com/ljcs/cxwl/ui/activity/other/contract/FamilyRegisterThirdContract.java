package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterThirdActivity
 * @Description: $description
 * @date 2018/06/27 19:46:50
 */
public interface FamilyRegisterThirdContract {
    interface View extends BaseView<FamilyRegisterThirdContractPresenter> {
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

    interface FamilyRegisterThirdContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void allInfo(Map map);
    }
}