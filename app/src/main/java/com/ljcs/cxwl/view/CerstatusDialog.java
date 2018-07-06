package com.ljcs.cxwl.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.vondear.rxtools.view.dialog.RxDialog;


/**
 * @author xlei
 * @Date 2018/6/27.
 */

public class CerstatusDialog extends RxDialog {
    ImageView imageView;
    TextView tv1;
    TextView tv2;
    Button btn;
    private CerstatusDialog certificationDialog;

    public CerstatusDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public CerstatusDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public CerstatusDialog(Context context) {
        super(context);
        initView();
    }

    public CerstatusDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public CerstatusDialog(Activity context) {
        super(context);
        initView();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    private void initView() {
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_cerstatus, null);
        btn = (Button) dialogView.findViewById(R.id.btn);
        tv1 = (TextView) dialogView.findViewById(R.id.tv1);
        tv2 = (TextView) dialogView.findViewById(R.id.tv2);
        imageView = (ImageView) dialogView.findViewById(R.id.imageView);
        setContentView(dialogView);
    }
}
