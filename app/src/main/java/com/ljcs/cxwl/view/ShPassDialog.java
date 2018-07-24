package com.ljcs.cxwl.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.entity.ScanBean;

import butterknife.BindView;


/**
 * @author xlei
 * @Date 2018/6/27.
 */

public class ShPassDialog extends Dialog {


    TextView tvMingcheng;
    TextView tvRiqi;
    TextView tvKaifa;
    TextView tvDizhi;
    TextView tvTaoshu;
    TextView tvXuhao;
    Button  btn;

    public ShPassDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public ShPassDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public ShPassDialog(Context context) {
        super(context);
        initView();
    }


    public ShPassDialog(Activity context) {
        super(context);
        initView();
    }


    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_scan, null);
        tvMingcheng=dialogView.findViewById(R.id.tv_mingcheng);
        tvRiqi = dialogView.findViewById(R.id.tv_riqi);
        tvDizhi = dialogView.findViewById(R.id.tv_dizhi);
        tvKaifa = dialogView.findViewById(R.id.tv_kaifa);
        tvXuhao = dialogView.findViewById(R.id.tv_xuhao);
        tvTaoshu = dialogView.findViewById(R.id.tv_taoshu);
        btn = dialogView.findViewById(R.id.btn);

        setContentView(dialogView);
    }
    public void setData(ScanBean.ScanData data){
        tvMingcheng.setText(data.getXmmc());
        tvRiqi.setText(data.getKprq()+"至"+data.getJsrq());
        tvXuhao.setText(data.getRgxh()+"");
        tvDizhi.setText(data.getXmdz());
        tvKaifa.setText(data.getKfgsmc());
        tvTaoshu.setText(data.getKsts()+"套/"+data.getGxts()+"套");

    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
