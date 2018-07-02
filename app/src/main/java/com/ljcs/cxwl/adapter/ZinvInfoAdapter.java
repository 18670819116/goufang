package com.ljcs.cxwl.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.entity.CertificationInfo;

import java.util.List;

/**
 * @author xlei
 * @Date 2018/7/2.
 */

public class ZinvInfoAdapter extends BaseQuickAdapter<CertificationInfo, BaseViewHolder> {
    public ZinvInfoAdapter(@Nullable List<CertificationInfo> data) {
        super(R.layout.adapter_zinv_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CertificationInfo item) {
        helper.setText(R.id.tv_name, item.getName_zinv()).setText(R.id
                .tv_leixing1, item.getSex_zinv()).setText(R.id.tv_leixing2,item.getLeixing1_zinv())
                .setText(R.id.tv_leixing3,item.getLeixing2_zinv()).setText(R.id.tv_leixing4,item.getLeixing3_zinv())
                .setText(R.id.tv_leixing5,item.getGuangxi_zinv()).setText(R.id.tv_idcard,item.getIdcard_zinv());
        ImageView view = helper.getView(R.id.img_upload);
        Glide.with(mContext).load(item.getPic_path_hk_zinv()).skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE).into(view);

    }
}
