package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterTwo1Activity
 * @Description: $description
 * @date 2018/07/11 15:00:45
 */
public interface FamilyRegisterTwo1Contract {
    interface View extends BaseView<FamilyRegisterTwo1ContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void matesInfoSuccess(MatesInfo baseEntity);
    }

    interface FamilyRegisterTwo1ContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void matesInfo(Map map);

        void uploadPic(List<String> list, UploadFileCallBack callBack);
    }
}