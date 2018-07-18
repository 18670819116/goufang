package com.ljcs.cxwl.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;


/**
 * @author xlei
 * @Date 2018/6/27.
 */

public class CertificationDialog extends Dialog {
    ImageView imageView;
    TextView tv1;
    TextView tv2;
    Button btn;
    private CertificationDialog certificationDialog;

    public CertificationDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public CertificationDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public CertificationDialog(Context context) {
        super(context);
        initView();
    }



    public CertificationDialog(Activity context) {
        super(context,R.style.BaseDialog);
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
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_certification, null);
        btn = (Button) dialogView.findViewById(R.id.btn);
        tv1 = (TextView) dialogView.findViewById(R.id.tv1);
        tv2 = (TextView) dialogView.findViewById(R.id.tv2);
        imageView = (ImageView) dialogView.findViewById(R.id.imageView);
        setContentView(dialogView);
    }
}
