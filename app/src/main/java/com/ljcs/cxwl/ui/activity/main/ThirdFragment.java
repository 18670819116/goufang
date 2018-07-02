package com.ljcs.cxwl.ui.activity.main;

import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseFragment;
import com.ljcs.cxwl.ui.activity.main.component.DaggerThirdComponent;
import com.ljcs.cxwl.ui.activity.main.contract.ThirdContract;
import com.ljcs.cxwl.ui.activity.main.module.ThirdModule;
import com.ljcs.cxwl.ui.activity.main.presenter.ThirdPresenter;

import javax.inject.Inject;

/**
 * @author xlei
 * @Package com.example.ai.ui.activity.main
 * @Description: $description
 * @date 2017/10/18 14:21:40
 */

public class ThirdFragment extends BaseFragment implements ThirdContract.View {

    @Inject
    ThirdPresenter mPresenter;

//   @Nullable
//   @Override
//   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//       View view = inflater.inflate(R.layout.fragment_third, null);
//       ButterKnife.bind(this, view);
//       Bundle mBundle = getArguments();
//       return view;
//   }


    @Override
    protected void setupFragmentComponent() {
       DaggerThirdComponent
               .builder()
               .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
               .thirdModule(new ThirdModule(this))
               .build()
               .inject(this);
    }
    @Override
    public void setPresenter(ThirdContract.ThirdContractPresenter presenter) {
        mPresenter = (ThirdPresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        //progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        //progressDialog.hide();
    }

}