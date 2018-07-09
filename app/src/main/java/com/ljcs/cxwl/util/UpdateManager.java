package com.ljcs.cxwl.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.ljcs.cxwl.R;
import com.ljcs.cxwl.application.AppConfig;
import com.ljcs.cxwl.ui.activity.main.SplashActivity;
import com.vondear.rxtools.RxAppTool;
import com.vondear.rxtools.RxDeviceTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 更新管理器
 */
@SuppressLint({ "InflateParams", "HandlerLeak", "SdCardPath" })
public class UpdateManager {

	private Context mContext;

	// 提示语
	private String updateMsg = "有最新的软件包哦，亲快下载吧~";

	// 返回的安装包url
	private String apkUrl = "";

	private Dialog noticeDialog;

	private Dialog downloadDialog;
	/* 下载包安装路径 */
	private static final String savePath = "/sdcard/updatedemo/";

	private static final String saveFileName = savePath
			+ "UpdateDemoRelease.apk";

	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;

	private TextView curProgress ,totalProgress,cancel;

	private static final int DOWN_UPDATE = 1;

	private static final int DOWN_OVER = 2;

	private int progress;

	private Thread downLoadThread;

	private boolean interceptFlag = false;
	private  Handler mhander;

	private OnYiHouOnClickListener onYiHouOnClickListener;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				Log.d("geek","progress"+progress);
				curProgress.setText(progress+"%");
				totalProgress.setText(progress+"/100");
				break;
			case DOWN_OVER:
				installApk();
				break;
			default:
				break;
			}
		}
    };

	public UpdateManager(Context context, String apkUrl) {
		this.mContext = context;
		this.apkUrl = apkUrl;
	}

	public UpdateManager(Context context, String apkUrl, Handler handler) {
		this.mContext = context;
		this.apkUrl = apkUrl;
		this.mhander = handler;
		Log.d("geek","apkUrl"+apkUrl);
	}

	// 外部接口让主Activity调用
	public void checkUpdateInfo(String versionCode,String versionMsg,Integer versionIsCompulsory) {
		showNoticeDialog(versionCode,versionMsg,versionIsCompulsory);
	}

	private void showNoticeDialog(String versionCode,String versionMsg,Integer versionIsCompulsory) {
		Builder builder = new Builder(mContext);
		builder.setTitle("版本更新"+versionCode);
		builder.setMessage(updateMsg+"\n"+"版本号:"+versionCode+"\n"+"版本描述:"+versionMsg);
		builder.setCancelable(false);
		//强制更新
		if(versionIsCompulsory == 1){
			builder.setPositiveButton("立即更新", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showDownloadDialog(true);
				}
			});
		}else{
			builder.setPositiveButton("立即更新", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showDownloadDialog(false);
				}
			});
			builder.setNegativeButton("以后再说", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (onYiHouOnClickListener != null) {
						onYiHouOnClickListener.onYihouClick();
					}
					dialog.dismiss();
					if(mhander != null){
						mhander.sendEmptyMessage(SplashActivity.LOCATION_FINISH);
					}
				}
			});
		}
		noticeDialog = builder.create();
		noticeDialog.show();
	}

	private void showDownloadDialog(boolean type) {
		Builder builder = new Builder(mContext);
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.dialog_dwon_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);
		curProgress = (TextView)v.findViewById(R.id.curProgress);
		totalProgress = (TextView)v.findViewById(R.id.totalProgress);
		cancel = (TextView) v.findViewById(R.id.cancel);
		builder.setView(v);
		builder.setCancelable(false);
		if(type){
			cancel.setVisibility(View.GONE);
		}else{
			cancel.setVisibility(View.VISIBLE);
		}
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(downloadDialog != null){
					downloadDialog.dismiss();
				}
				interceptFlag = true;
				if(mhander != null){
					mhander.sendEmptyMessage(SplashActivity.LOCATION_FINISH);
				}
				onYiHouOnClickListener.onYihouClick();
			}
		});
		downloadDialog = builder.create();
		downloadDialog.setCancelable(false);
		downloadDialog.show();

		downloadApk();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// 点击取消就停止下载.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	/**
	 * 下载apk
	 */

	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 安装apk
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
//				"application/vnd.android.package-archive");
//		mContext.startActivity(i);
//		RxAppTool.InstallAPK(mContext, apkfile.toString());
		RxAppTool.installApp(mContext,apkfile);

	}

	public interface OnYiHouOnClickListener{
		void onYihouClick();
	}


	public void setOnYiHouOnClickListener(OnYiHouOnClickListener onYiHouOnClickListener) {
		this.onYiHouOnClickListener = onYiHouOnClickListener;
	}
}
