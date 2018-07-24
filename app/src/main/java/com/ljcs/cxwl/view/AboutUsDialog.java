package com.ljcs.cxwl.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ljcs.cxwl.R;

/**
 * @author xlei
 * @Date 2018/7/24.
 */

public class AboutUsDialog extends Dialog {
    private ImageView gif;
    private ImageView cancel;
    public AboutUsDialog(@NonNull Context context) {
        super(context,R.style.dialog_translucent);
        initView();
    }
    public AboutUsDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }


    public AboutUsDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }
    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_about_us, null);
       gif= dialogView.findViewById(R.id.img_gif);
       cancel= dialogView.findViewById(R.id.img_ca);
        Glide.with(getContext()).load(R.mipmap.ic_about_us_f).into(gif);
        setContentView(dialogView);
    }

    public ImageView getGif() {
        return gif;
    }

    public void setGif(ImageView gif) {
        this.gif = gif;
    }

    public ImageView getCancel() {
        return cancel;
    }

    public void setCancel(ImageView cancel) {
        this.cancel = cancel;
    }
}
