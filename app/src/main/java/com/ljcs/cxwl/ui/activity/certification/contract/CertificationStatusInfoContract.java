package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CertificationStatusInfoActivity
 * @Description: $description
 * @date 2018/07/03 21:25:58
 */
public interface CertificationStatusInfoContract {
    interface View extends BaseView<CertificationStatusInfoContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void cerInfoDetailSuccess(CerInfo baseEntity);
    }

    interface CertificationStatusInfoContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void cerInfoDetail(Map map);
    }
}