package com.ljcs.cxwl.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ljcs.cxwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xlei
 * @Date 2018/6/28.
 */

public class ShowImgActivity extends AppCompatActivity {


    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将window扩展至全屏，并且不会覆盖状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //避免在状态栏的显示状态发生变化时重新布局
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_showimg);
        ButterKnife.bind(this);
        Glide.with(this).load(getIntent().getStringExtra("img_path")).skipMemoryCache(true).diskCacheStrategy
                (DiskCacheStrategy.NONE).into(imageView);

    }

    @OnClick(R.id.imageView)
    public void onViewClicked() {
        finish();
    }
}
