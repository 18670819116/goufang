package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.HujiInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterActivity
 * @Description: $description
 * @date 2018/06/27 17:51:07
 */
public interface FamilyRegisterContract {
    interface View extends BaseView<FamilyRegisterContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void getQiniuTokenSuccess(QiniuToken qiniuToken);

        void hukouInfoSuccess(HujiInfo baseEntity);
    }

    interface FamilyRegisterContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
        void hukouInfo(Map map);
        void getQiniuToken();
    }
}