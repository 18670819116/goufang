package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MainActivity
 * @Description: $description
 * @date 2018/07/02 16:17:40
 */
public interface MainContract {
    interface View extends BaseView<MainContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void cerInfoDetailSuccess(CerInfo baseEntity);

        void closeProgressDialog();

        void allInfoSuccess(AllInfo baseEntity);
    }

    interface MainContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void allInfo(Map map);

        void cerInfoDetail(Map map);
    }
}