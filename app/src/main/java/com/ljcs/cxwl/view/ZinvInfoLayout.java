package com.ljcs.cxwl.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;
import com.ljcs.cxwl.data.api.API;
import com.ljcs.cxwl.entity.AllInfo;

/**
 * @author xlei
 * @Date 2018/7/4.
 */

public class ZinvInfoLayout extends LinearLayout {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tvHjszd;
    private TextView tv_change;
    private ImageView img1;
    private ImageView img2;

    public ZinvInfoLayout(Context context, AllInfo.Data.ZinvBean bean) {
        super(context);
        initViews(context,bean);

//        tv1.setText(bean.getXm());

    }

    private void initViews(Context context, AllInfo.Data.ZinvBean bean) {
        LayoutInflater.from(context).inflate(R.layout.layout_zinv_info, this, true);
        tv1 = (TextView) findViewById(R.id.tv_name);
        tv2 = (TextView) findViewById(R.id.tv_sex);
        tv3 = (TextView) findViewById(R.id.tv_hklx);
        tv4 = (TextView) findViewById(R.id.tv_hkxz);
        tv5 = (TextView) findViewById(R.id.tv_hyzk);
        tv6 = (TextView) findViewById(R.id.tv_card);
        tv7 = (TextView) findViewById(R.id.tv_gx);
        tvHjszd = (TextView) findViewById(R.id.tv_hjszd);
        tv_change = (TextView) findViewById(R.id.tv_change);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        tv1.setText(bean.getJtcy().getXm());
        tv2.setText(bean.getJtcy().getXb());
//        tv3.setText(bean.getJtcy().getHklx());
        tv4.setText(bean.getJtcy().getHjfl());
//        tv5.setText(bean.getJtcy().getHyzt());
        tv6.setText(bean.getJtcy().getZjhm());
//        tv7.setText(bean.getJtcy().getGx());
        tvHjszd.setText(bean.getJtcy().getHjszd());
        Glide.with(context).load(API.PIC+bean.getZzxx().getHkb()).into(img1);

       // Glide.with(context).load(API.PIC+bean.getSfzfm()).into(img2);
    }

    public ZinvInfoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZinvInfoLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextView getTv1() {
        return tv1;
    }

    public void setTv1(TextView tv1) {
        this.tv1 = tv1;
    }

    public TextView getTv2() {
        return tv2;
    }

    public void setTv2(TextView tv2) {
        this.tv2 = tv2;
    }

    public TextView getTv3() {
        return tv3;
    }

    public void setTv3(TextView tv3) {
        this.tv3 = tv3;
    }

    public TextView getTv4() {
        return tv4;
    }

    public void setTv4(TextView tv4) {
        this.tv4 = tv4;
    }

    public TextView getTv5() {
        return tv5;
    }

    public void setTv5(TextView tv5) {
        this.tv5 = tv5;
    }

    public TextView getTv6() {
        return tv6;
    }

    public void setTv6(TextView tv6) {
        this.tv6 = tv6;
    }

    public TextView getTv7() {
        return tv7;
    }

    public void setTv7(TextView tv7) {
        this.tv7 = tv7;
    }

    public TextView getTv_change() {
        return tv_change;
    }

    public void setTv_change(TextView tv_change) {
        this.tv_change = tv_change;
    }

    public ImageView getImg1() {
        return img1;
    }

    public void setImg1(ImageView img1) {
        this.img1 = img1;
    }

    public ImageView getImg2() {
        return img2;
    }

    public void setImg2(ImageView img2) {
        this.img2 = img2;
    }
}
