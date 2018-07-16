package com.ljcs.cxwl.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.base.BaseActivity;
import com.ljcs.cxwl.ui.activity.main.component.DaggerComplainComponent;
import com.ljcs.cxwl.ui.activity.main.contract.ComplainContract;
import com.ljcs.cxwl.ui.activity.main.module.ComplainModule;
import com.ljcs.cxwl.ui.activity.main.presenter.ComplainPresenter;
import com.vondear.rxtool.RxKeyboardTool;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        mPhotosSnpl.setDelegate(this);
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
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
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
}