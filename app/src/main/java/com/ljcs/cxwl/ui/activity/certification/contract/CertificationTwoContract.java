package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CertificationTwoActivity
 * @Description: $description
 * @date 2018/06/26 19:26:58
 */
public interface CertificationTwoContract {
    interface View extends BaseView<CertificationTwoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void getQiniuTokenSuccess(QiniuToken qiniuToken);

        void postInfoSuccess(CerInfo baseEntity);

        void allInfoSuccess(AllInfo baseEntity);
    }

    interface CertificationTwoContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void postInfo(Map map);

        void allInfo(Map map);

        void uploadFile(Map map, String file);
    }
}