package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MatesInfoFourActivity
 * @Description: $description
 * @date 2018/06/27 16:35:11
 */
public interface MatesInfoFourContract {
    interface View extends BaseView<MatesInfoFourContractPresenter> {
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

        void allInfoSuccess(AllInfo baseEntity);
    }

    interface MatesInfoFourContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void matesInfo(Map map);

        void allInfo(Map map);
    }
}