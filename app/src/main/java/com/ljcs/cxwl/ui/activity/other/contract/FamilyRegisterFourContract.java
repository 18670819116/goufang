package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterFourActivity
 * @Description: $description
 * @date 2018/06/27 20:03:29
 */
public interface FamilyRegisterFourContract {
    interface View extends BaseView<FamilyRegisterFourContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void allInfoSuccess(AllInfo baseEntity);

        void commitInfoSuccess(BaseEntity baseEntity);

        void matesInfoSaveSuccess(BaseEntity baseEntity);
    }

    interface FamilyRegisterFourContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void allInfo(Map map);

        void commitInfo(Map map);

        void matesInfoSave(Map map);

    }
}