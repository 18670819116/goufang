package com.ljcs.cxwl.ui.activity.changephone.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ChangePhoneFourActivity
 * @Description: $description
 * @date 2018/07/19 20:33:44
 */
public interface ChangePhoneFourContract {
    interface View extends BaseView<ChangePhoneFourContractPresenter> {
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

    interface ChangePhoneFourContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getChangeCode(String phone);

        void changePhone(Map map);
    }
}