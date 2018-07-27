package com.ljcs.cxwl.util;

import android.content.Context;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.ljcs.cxwl.contain.ShareStatic;
import com.orhanobut.logger.Logger;
import com.vondear.rxtool.RxSPTool;

/**
 * @author xlei
 * @Date 2018/7/19.
 */

public class ClearUtils {
    public static void clearRxSp(Context context) {
        RxSPTool.remove(context, ShareStatic.APP_LOGIN_SJHM);
        RxSPTool.remove(context, ShareStatic.APP_LOGIN_TOKEN);
        RxSPTool.remove(context, ShareStatic.APP_LOGIN_MM);
        RxSPTool.remove(context, ShareStatic.APP_LOGIN_BH);
        RxSPTool.remove(context, ShareStatic.APP_LOGIN_ZT);
        removeAlPush();
    }

    private static void removeAlPush() {
        PushServiceFactory.getCloudPushService().removeAlias(null, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Logger.i("阿里云移除别名成功" + "s" + s);
            }

            @Override
            public void onFailed(String s, String s1) {
                Logger.e("阿里云移除别名失败" + "s" + s + "s1" + s1);
            }
        });
        PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Logger.i("阿里云移除账号成功" + "s" + s);
            }

            @Override
            public void onFailed(String s, String s1) {
                Logger.e("阿里云移除账号失败" + "s" + s + "s1" + s1);

            }
        });
    }
}
