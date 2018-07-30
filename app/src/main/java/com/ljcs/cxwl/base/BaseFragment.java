package com.ljcs.cxwl.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ljcs.cxwl.view.ProgressDialog;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;
    public ProgressDialog progressDialog;

    protected abstract void setupFragmentComponent();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent();
        initDataFromLocal();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        progressDialog = new ProgressDialog(activity);
//		progressDialog.setMyCancelListener(this);
    }

    protected abstract void initDataFromLocal();
}
