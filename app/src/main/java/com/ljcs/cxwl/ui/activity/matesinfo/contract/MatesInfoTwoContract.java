package com.ljcs.cxwl.ui.activity.matesinfo.contract;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.entity.MatesInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for MatesInfoTwoActivity
 * @Description: $description
 * @date 2018/06/27 16:33:03
 */
public interface MatesInfoTwoContract {
    interface View extends BaseView<MatesInfoTwoContractPresenter> {
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

    interface MatesInfoTwoContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void getQiniuToken();

        void matesInfo(Map map);

        void allInfo(Map map);
    }
}