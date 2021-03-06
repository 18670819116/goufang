package com.ljcs.cxwl.contain;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CertificationInfo;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public class Contains {
    public static final int REQUEST_CODE_GENERAL_BASIC = 103;//户口本
    public static final int REQUEST_CODE_GENERAL_LHZ = 105;//离婚
    public static final int REQUEST_CODE_GENERAL_JHZ = 104;//结婚证
    public static final int REQUEST_CODE_CAMERA = 101;//身份证面
    public static final int REQUEST_CODE_CAMERA_FAN = 102;//身份反面
    public static final int REQUEST_SUCCESS = 200;//网络请求成功
    public static final int FAST_CLICK = 1000;//防止点击过快的毫秒数
    public static final String OCR_AK = "GFGaFccpziGPV9RTlVdwGrGv";//OCR AK
    public static final String OCR_SK = "kZiEoEO7PIwR0T0YVEQ4PZdcTOKV7L0y";//OCR SK
    public static CertificationInfo sCertificationInfo = new CertificationInfo(); //实名认证本地保存信息
    public static int ENTERTYPE_CHANGE = 1;//2从正常进入1从修改进入添加添加配偶
    public static AllInfo sAllInfo = new AllInfo();
    public static final String REGEX_MOBILE_EXACT = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

}
