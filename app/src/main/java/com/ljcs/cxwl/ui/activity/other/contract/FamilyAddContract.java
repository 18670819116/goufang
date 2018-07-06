package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyAddActivity
 * @Description: $description
 * @date 2018/07/02 10:34:10
 */
public interface FamilyAddContract {
    interface View extends BaseView<FamilyAddContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void matesInfoZinvSuccess(MatesInfo baseEntity);

        void getQiniuTokenSuccess(QiniuToken qiniuToken);

        void matesInfoDeleteSuccess(BaseEntity baseEntity);
    }

    interface FamilyAddContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void matesInfoZinv(Map map);

        void matesInfoDelete(Map map);
    }
}