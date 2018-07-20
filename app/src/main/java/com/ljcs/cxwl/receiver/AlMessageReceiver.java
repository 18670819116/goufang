package com.ljcs.cxwl.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.ljcs.cxwl.ui.activity.certification.CertificationStatusInfoActivity;
import com.ljcs.cxwl.ui.activity.main.LoginActivity;
import com.ljcs.cxwl.ui.activity.other.FamilyRegisterStatusActivity;
import com.ljcs.cxwl.util.AppManager;
import com.ljcs.cxwl.util.ClearUtils;
import com.ljcs.cxwl.util.ToastUtil;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author xlei
 * @Date 2018/7/18.
 */

public class AlMessageReceiver extends MessageReceiver {
    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";
    /**
     * 实名认证审核
     */
//    SMRZSH("smrzsh"),

    /**
     * 购房资格审核
     */
//    GFZGSH("gfzgsh"),

    /**
     * 实名认证申诉
     */
//    SMRZSS("smrzss"),

    /**
     * 购房资格申诉
     */
//    GFZGSS("gfzgss");
    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        Log.e("MyMessageReceiver", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: "
                + extraMap);
        Log.d("geek", "openNotification: extras" + extraMap.toString());
        String customs = extraMap.get("ext");
        Intent intent = new Intent();
        if (customs != null && "alias".equals(customs)) {
            //收到新通知推送  退出登录
            ClearUtils.clearRxSp(context);
            intent.setClass(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            ToastUtil.showCenterShort("您的账号在另一台设备上登录，如非本人操作，请及时修改密码");
            context.startActivity(intent);
            AppManager.getInstance().finishAllActivity();
        } else if (customs != null && "smrzsh".equals(customs)) {

        }
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " +
                cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:"
                + extraMap);
        Log.d("geek", "openNotification: extras" + extraMap.toString());
        String customs ="";
        Intent intent = new Intent();
        try {
            JSONObject extrasJson = new JSONObject(extraMap);
            customs = extrasJson.optString("ext");
        } catch (Exception e) {
            return;
        }
        if ( "smrzsh".equals(customs)) {
            intent.setClass(context, CertificationStatusInfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        } else if ("gfzgsh".equals(customs)) {
            intent.setClass(context, FamilyRegisterStatusActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        } else if ( "smrzss".equals(customs)) {
//            intent.setClass(context, ComplainListActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
        } else if ("gfzgss".equals(customs)) {
//            intent.setClass(context, DeviceActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
        }
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary +
                ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String>
            extraMap, int openType, String openActivity, String openUrl) {
        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", " +
                "extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" +
                openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.e("MyMessageReceiver", "onNotificationRemoved");
    }
}
