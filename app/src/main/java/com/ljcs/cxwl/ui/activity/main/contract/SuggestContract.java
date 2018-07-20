package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.entity.CommonBean;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author xlei
 * @Package The contract for SuggestActivity
 * @Description: $description
 * @date 2018/07/06 15:46:33
 */
public interface SuggestContract {
    interface View extends BaseView<SuggestContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void commitSuggestSuccess(CommonBean commonBean);
    }

    interface SuggestContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void commitSuggest(Map map);
    }
}