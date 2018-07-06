package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterTwoActivity
 * @Description: $description
 * @date 2018/06/27 19:05:45
 */
public interface FamilyRegisterTwoContract {
    interface View extends BaseView<FamilyRegisterTwoContractPresenter> {
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

    interface FamilyRegisterTwoContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);
void getQiniuToken();

        void matesInfo(Map map);
    }
}