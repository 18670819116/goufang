package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for PersonCenterActivity
 * @Description: $description
 * @date 2018/07/03 08:33:38
 */
public interface PersonCenterContract {
    interface View extends BaseView<PersonCenterContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void loginOutSuccess(BaseEntity baseEntity);
    }

    interface PersonCenterContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
        void loginOut(Map map);
    }
}