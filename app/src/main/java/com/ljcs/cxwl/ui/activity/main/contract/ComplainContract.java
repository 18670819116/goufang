package com.ljcs.cxwl.ui.activity.main.contract;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for ComplainActivity
 * @Description: $description
 * @date 2018/07/09 10:58:14
 */
public interface ComplainContract {
    interface View extends BaseView<ComplainContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();

        void commitShSuggestSuccess(BaseEntity baseEntity);
    }

    interface ComplainContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
//        void getBusinessInfo(Map map);
        void uploadPic(List<String> list, UploadFileCallBack callBack);

        void commitShSuggest(Map map);
    }
}