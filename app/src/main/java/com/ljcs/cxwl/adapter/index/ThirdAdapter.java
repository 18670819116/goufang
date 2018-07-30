package com.ljcs.cxwl.adapter.index;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljcs.cxwl.R;

/**
 * @author xlei
 * @Date 2018/7/30.
 */

public class ThirdAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ThirdAdapter() {
        super(R.layout.adapter_img);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img = helper.getView(R.id.img);
        Glide.with(mContext).load(item).into(img);

    }
}
