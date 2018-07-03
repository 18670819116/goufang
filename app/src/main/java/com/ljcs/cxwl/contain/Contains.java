package com.ljcs.cxwl.contain;

import com.ljcs.cxwl.entity.CertificationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuan.Y.Q
 * @Date 2017/9/26.
 */

public class Contains {
    public static CertificationInfo sCertificationInfo = new CertificationInfo();
    public static List<CertificationInfo> sCertificationInfoList = new ArrayList<>();
    public static final int REQUEST_CODE_GENERAL_BASIC = 103;//户口本
    public static final int REQUEST_CODE_GENERAL_JHZ = 104;//结婚证
    public static final int REQUEST_CODE_CAMERA = 101;//身份证面
    public static final int REQUEST_CODE_CAMERA_FAN = 102;//身份反面
    public static final int REQUEST_SUCCESS = 200;//网络请求成功

}
