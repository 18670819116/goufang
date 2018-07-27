package com.ljcs.cxwl.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;

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

    protected abstract void initDataFromLocal();
}
