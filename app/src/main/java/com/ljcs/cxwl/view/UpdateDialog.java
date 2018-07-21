package com.ljcs.cxwl.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljcs.cxwl.R;
import com.vondear.rxui.view.RxProgressBar;

/**
 * @author xlei
 * @Date 2018/7/21.
 */

public class UpdateDialog extends Dialog {
    private ImageView cancel;
    private Button confirm;
    private TextView tvVersion;
    private TextView tvContent;
    private RxProgressBar progressBar;

    public UpdateDialog(@NonNull Context context) {
        super(context, R.style.dialog_translucent);
        initView();
    }


    public UpdateDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected UpdateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_update, null);
        cancel = dialogView.findViewById(R.id.img_cancel);
        confirm = dialogView.findViewById(R.id.btn_confirm);
        tvVersion = dialogView.findViewById(R.id.tv_version);
        tvContent = dialogView.findViewById(R.id.tv_content);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        progressBar = dialogView.findViewById(R.id.progressBar);
        setContentView(dialogView);
    }

    public void setVersionText(String versionText) {
        tvVersion.setText(versionText);
    }

    public void setContentText(String contentText) {
        tvContent.setText(contentText);
    }

    public ImageView getCancel() {
        return cancel;
    }

    public void setCancel(ImageView cancel) {
        this.cancel = cancel;
    }

    public Button getConfirm() {
        return confirm;
    }

    public void setConfirm(Button confirm) {
        this.confirm = confirm;
    }

    public TextView getTvVersion() {
        return tvVersion;
    }

    public void setTvVersion(TextView tvVersion) {
        this.tvVersion = tvVersion;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public RxProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(RxProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
