package com.ljcs.cxwl.contain;

import com.ljcs.cxwl.entity.AllInfo;
import com.ljcs.cxwl.entity.CerInfo;
import com.ljcs.cxwl.entity.CertificationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public class Contains {
    public static CertificationInfo sCertificationInfo = new CertificationInfo(); //实名认证本地保存信息
    public static int ENTERTYPE = 1;//1表示家庭成员进入  2 表示申购进入
    public static int ENTERTYPE_CHANGE = 1;//2从正常进入1从修改进入添加添加配偶
    public static AllInfo sAllInfo = new AllInfo();
    public static final int REQUEST_CODE_GENERAL_BASIC = 103;//户口本
    public static final int REQUEST_CODE_GENERAL_LHZ = 105;//离婚
    public static final int REQUEST_CODE_GENERAL_JHZ = 104;//结婚证
    public static final int REQUEST_CODE_CAMERA = 101;//身份证面
    public static final int REQUEST_CODE_CAMERA_FAN = 102;//身份反面
    public static final int REQUEST_SUCCESS = 200;//网络请求成功
    public static final int FAST_CLICK = 1000;//防止点击过快的毫秒数
    public static final String OCR_AK = "gPFYMZ1xIUMZP8C44xdPVbso";//OCR AK
    public static final String OCR_SK = "O3ADFGC344Wvrc94gmX9d5gK779P1O9A";//OCR SK

}
