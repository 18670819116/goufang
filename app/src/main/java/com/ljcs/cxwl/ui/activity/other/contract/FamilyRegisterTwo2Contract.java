package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterTwo2Activity
 * @Description: $description
 * @date 2018/07/10 10:43:02
 */
public interface FamilyRegisterTwo2Contract {
    interface View extends BaseView<FamilyRegisterTwo2ContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void matesInfoSuccess(MatesInfo baseEntity);

        void getQiniuTokenSuccess(QiniuToken qiniuToken);
    }

    interface FamilyRegisterTwo2ContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void matesInfo(Map map);
    }
}