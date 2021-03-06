package com.ljcs.cxwl.ui.activity.changephone.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ChangePhoneTwoActivity
 * @Description: $description
 * @date 2018/07/09 16:39:17
 */
public interface ChangePhoneTwoContract {
    interface View extends BaseView<ChangePhoneTwoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getChangeCodeSuccess(CommonBean commonBean);

        void changePhoneSuccess(BaseEntity baseEntity);
    }

    interface ChangePhoneTwoContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getChangeCode(String phone);

        void changePhone(Map map);
    }
}