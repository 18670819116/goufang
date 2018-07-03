package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ChangePwdActivity
 * @Description: $description
 * @date 2018/07/03 13:37:53
 */
public interface ChangePwdContract {
    interface View extends BaseView<ChangePwdContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void changePwdSuccess(RegisterBean baseEntity);
    }

    interface ChangePwdContractPresenter extends BasePresenter {
//        /**
//         *
//         */
        void changePwd(Map map);
    }
}