package com.ljcs.cxwl.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.callback.UploadFileCallBack;
import com.ljcs.cxwl.contain.Contains;
import com.ljcs.cxwl.contain.ShareStatic;
import com.ljcs.cxwl.entity.BaseEntity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerComplainComponent;
import com.ljcs.cxwl.ui.activity.main.contract.ComplainContract;
import com.ljcs.cxwl.ui.activity.main.module.ComplainModule;
import com.ljcs.cxwl.ui.activity.main.presenter.ComplainPresenter;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxDataTool;
import com.vondear.rxtool.RxSPTool;
import com.vondear.rxtool.RxTool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;

/**
 * @author xlei
 * @Package com.ljcs.cxwl.ui.activity.main
 * @Description: $description
 * @date 2018/07/09 10:58:14
 */

public class ComplainActivity extends BaseActivity implements ComplainContract.View, BGASortableNinePhotoLayout
        .Delegate {

    @Inject
    ComplainPresenter mPresenter;
    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;

    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("资格审查修改申诉");
//        toolbarMenu.setVisibility(View.VISIBLE);
//        toolbarMenu.setText("申诉历史");
        mPhotosSnpl.setDelegate(this);
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCount.setText(s.length() + "/300");
                if (s.length() >=300) {
                    ToastUtil.showCenterShort("申诉内容不能超过300个字符");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//            s.toString();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setupActivityComponent() {
        DaggerComplainComponent.builder().appComponent(((AppConfig) getApplication()).getApplicationComponent())
                .complainModule(new ComplainModule(this)).build().inject(this);
    }

    @Override
    public void setPresenter(ComplainContract.ComplainContractPresenter presenter) {
        mPresenter = (ComplainPresenter) presenter;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void commitShSuggestSuccess(BaseEntity baseEntity) {
        if (baseEntity.code == Contains.REQUEST_SUCCESS) {
            ToastUtil.showCenterShort(baseEntity.msg);
            finish();
        } else {
            onErrorMsg(baseEntity.code, baseEntity.msg);
        }

    }

    private void choicePhotoWrapper() {
        // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
        File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");
        Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this).cameraFileDir(takePhotoDir) //
                // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                .selectedPhotos(null) // 当前已选中的图片路径集合
                .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                .build();
        startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position,
                                        ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int
            position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position,
                                     String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this).previewPhotos(models)
                // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int
            toPosition, ArrayList<String> models) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));

        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (RxDataTool.isNullString(etContent.getText().toString())) {
            ToastUtil.showCenterShort("申诉内容不能为空");
            return;
        }
        if (mPhotosSnpl == null || mPhotosSnpl.getData() == null || mPhotosSnpl.getData().size() < 1) {
            ToastUtil.showCenterShort("至少上传一张图片");
            return;
        }
        if (RxTool.isFastClick(Contains.FAST_CLICK)) {
            return;
        }
        showProgressDialog();
        mPresenter.uploadPic(mPhotosSnpl.getData(), new UploadFileCallBack() {
            @Override
            public void sucess(List<String> url) {
                Map<String, String> map = new HashMap<>();
                map.put("token", RxSPTool.getString(ComplainActivity.this, ShareStatic.APP_LOGIN_TOKEN));
                map.put("xgyy", etContent.getText().toString());
                map.put("xglx", "2");
                if (mPhotosSnpl.getData().size() == 1) {
                    map.put("img1", url.get(0));
                } else if (mPhotosSnpl.getData().size() == 2) {
                    map.put("img1", url.get(0));
                    map.put("img2", url.get(1));
                } else if (mPhotosSnpl.getData().size() == 3) {
                    map.put("img1", url.get(0));
                    map.put("img2", url.get(1));
                    map.put("img3", url.get(2));
                }

                mPresenter.commitShSuggest(map);
            }

            @Override
            public void fail(String msg) {
                closeProgressDialog();
            }
        });
//

    }
}