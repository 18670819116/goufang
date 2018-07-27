package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CertificationActivity
 * @Description: $description
 * @date 2018/07/11 08:32:05
 */
public interface CertificationContract {
    interface View extends BaseView<CertificationContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void postInfoSuccess(CerInfo baseEntity);

    }

    interface CertificationContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void uploadPic(List<String> list, UploadFileCallBack callBack);

        void postInfo(Map map);
    }
}