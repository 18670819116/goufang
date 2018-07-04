package com.ljcs.cxwl.ui.activity.certification.contract;

import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for CertificationFiveActivity
 * @Description: $description
 * @date 2018/06/26 19:29:05
 */
public interface CertificationFiveContract {
    interface View extends BaseView<CertificationFiveContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void cerInfoLastSuccess(BaseEntity baseEntity);
    }

    interface CertificationFiveContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
        void cerInfoLast(Map map);
    }
}