package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ForgetPwdActivity
 * @Description: $description
 * @date 2018/06/26 16:39:54
 */
public interface ForgetPwdContract {
    interface View extends BaseView<ForgetPwdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getCode(CommonBean baseEntity);

        void forgetPwd(CommonBean baseEntity);
    }

    interface ForgetPwdContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getCode(String phone);
        void forgetPwd(Map map);
    }
}