package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CertificationFourActivity
 * @Description: $description
 * @date 2018/06/26 19:28:19
 */
public interface CertificationFourContract {
    interface View extends BaseView<CertificationFourContractPresenter> {
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

    interface CertificationFourContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void postInfo(Map map);

        void allInfo(Map map);
    }
}