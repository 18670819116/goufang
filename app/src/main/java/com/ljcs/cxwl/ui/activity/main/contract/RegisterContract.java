package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.entity.RegisterBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for RegisterActivity
 * @Description: $description
 * @date 2018/06/26 14:58:24
 */
public interface RegisterContract {
    interface View extends BaseView<RegisterContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getCode(CommonBean baseEntity);

        void register(RegisterBean baseEntity);
    }

    interface RegisterContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getCode(String phone);

        void register(Map map);
    }
}