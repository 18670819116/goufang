package com.ljcs.cxwl.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljcs.cxwl.R;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/16.
 */

public class ImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ImgAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_img, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.img));
    }
}
