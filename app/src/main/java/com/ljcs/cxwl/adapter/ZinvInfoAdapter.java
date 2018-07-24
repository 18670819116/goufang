package com.ljcs.cxwl.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.AllInfo;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/2.
 */

public class ZinvInfoAdapter extends BaseQuickAdapter<AllInfo.Data.ZinvBean, BaseViewHolder> {
    public ZinvInfoAdapter(@Nullable List<AllInfo.Data.ZinvBean> data) {
        super(R.layout.adapter_zinv_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllInfo.Data.ZinvBean item) {
        helper.setText(R.id.tv_name, item.getJtcy().getXm()).setText(R.id.tv_leixing1, item.getJtcy().getXb())
                .setText(R.id.tv_leixing3, item.getJtcy().getHjfl()).setText(R.id.tv_leixing4, item.getJtcy().getXm()
        ).setText(R.id.tv_leixing5, item.getJtcy().getHjszd()).setText(R.id.tv_idcard, item.getJtcy().getZjhm())
                .addOnClickListener(R.id.img_change).addOnClickListener(R.id.img_upload);
        ImageView view = helper.getView(R.id.img_upload);
        Glide.with(mContext).load(API.PIC + item.getZzxx().getHkb()).into(view);

    }
}
