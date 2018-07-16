package com.ljcs.cxwl.ui.activity.other.contract;

import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.HujiInfo;
import com.ljcs.cxwl.entity.QiniuToken;
import com.ljcs.cxwl.ui.activity.base.BasePresenter;
import com.ljcs.cxwl.ui.activity.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * @author xlei
 * @Package The contract for FamilyRegisterActivity
 * @Description: $description
 * @date 2018/06/27 17:51:07
 */
public interface FamilyRegisterContract {
    interface View extends BaseView<FamilyRegisterContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void getQiniuTokenSuccess(QiniuToken qiniuToken);

        void hukouInfoSuccess(HujiInfo baseEntity);
        void allInfoSuccess(AllInfo baseEntity);
    }

    interface FamilyRegisterContractPresenter extends BasePresenter {
        //        /**
//         *
//         */
        void hukouInfo(Map map);
        void getQiniuToken();
        void allInfo(Map map);
        void uploadPic(List<String> list, UploadFileCallBack callBack);

    }
}