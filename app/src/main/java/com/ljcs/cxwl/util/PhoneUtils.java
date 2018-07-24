package com.ljcs.cxwl.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

import static com.ljcs.cxwl.util.StringUitl.hasEmptyItem;

/**
 * @author xlei
 * @Date 2018/7/19.
 */

public class PhoneUtils {
    /**
     * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     * <p>
     * 渠道标志为：
     * 1，andriod（a）
     * <p>
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        StringBuilder deviceId = new StringBuilder();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!hasEmptyItem(imei)) {
                deviceId.append("m");
                deviceId.append(imei);
            }

            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!hasEmptyItem(sn)) {
                deviceId.append("s");
                deviceId.append(sn);
                Log.e("geek : ", "序列号（sn）=" + deviceId.toString());
            }

            return deviceId.toString();
        } catch (Exception e) {
            Log.d("geek", "getDeviceId: e");
            deviceId.append("e" + deviceId.toString());
        }
        return deviceId.toString();
    }

    /**
     * 获取基站信息
     *
     * @param context
     * @return
     */
    public static String getjizhaninfo(Context context) {

//        int[] info = new int[4];
        StringBuilder info = new StringBuilder();
        try {
            // String mnctype = "gsm";
            int lac = 0;
            int cellId = 0;
            int mcc = 0;
            int mnc = 0;
            TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int phoneType = mTelephonyManager.getPhoneType();
            // 返回值MCC + MNC
            String operator = mTelephonyManager.getNetworkOperator();

            if (operator != null && operator.length() > 3) {

                mcc = Integer.parseInt(operator.substring(0, 3));
                mnc = Integer.parseInt(operator.substring(3, operator.length() - 1));
            }
            // 中国移动和中国联通获取LAC、CID的方式
            if (phoneType == TelephonyManager.PHONE_TYPE_CDMA) {
                // mnctype = "cdma";
                CdmaCellLocation location = (CdmaCellLocation) mTelephonyManager.getCellLocation();

                lac = location.getNetworkId(); // getLac();
                cellId = location.getBaseStationId(); // getCid();
            } else if (phoneType == TelephonyManager.PHONE_TYPE_GSM) {
                // mnctype = "gsm";
                // GsmCellLocation location = (GsmCellLocation)
                // mTelephonyManager
                // .getCellLocation();
                GsmCellLocation location = null;
                CellLocation cellLocation = mTelephonyManager.getCellLocation();
                if (cellLocation != null) {
                    location = (GsmCellLocation) cellLocation;
                }
                if (location != null) {

                    lac = location.getLac();
                    cellId = location.getCid();
                }

            } else {
                // mnctype = "none";
            }
//            info[0] = cellId;
//            info[1] = lac;
//            info[2] = mcc;
//            info[3] = mnc;
            info.append(mcc + "-").append(mnc + "-").append(lac + "-").append(cellId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info.toString();
    }

    /**
     * 获取运营商
     *
     * @param mActivity
     * @return
     */
    public static String getOperators(Context mActivity) {
        TelephonyManager telManager = (TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = telManager.getSubscriberId();
        if (imsi != null) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                // 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
                return "中国移动";// 中国移动
            } else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
                return "中国联通";// 中国联通
            } else if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
                return "中国电信";// 中国电信
            }
        }
        return "";
    }

    enum NetState {
        WIFI("WIFI", 1), CDMA("2G", 2), UMTS("3G", 3), LTE("4G", 4), UNKOWN("unkonw", 5);
        private int state;
        private String type;

        NetState(String type, int state) {
            this.state = state;
            this.type = type;
        }
    }

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    public static String getCurrentNetType(Context context) {
        // String type = "unknown";
        int state = NetState.UNKOWN.state;
        String type = NetState.UNKOWN.type;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info == null) {
            // type = "unknown";
            state = NetState.UNKOWN.state;
            ;
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            // type = "wifi";
            state = NetState.WIFI.state;
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS ||
                    subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                // type = "2g";
                state = NetState.CDMA.state;
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager
                    .NETWORK_TYPE_HSDPA || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType ==
                    TelephonyManager.NETWORK_TYPE_EVDO_0 || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                // type = "3g";
                state = NetState.UMTS.state;
            } else {// LTE是3g到4g的过渡，是3.9G的全球标准 if (subType ==
                // TelephonyManager.NETWORK_TYPE_LTE)
                // type = "4g";
                state = NetState.LTE.state;
            }
        }
        switch (state) {
            case 1:
                type = NetState.WIFI.type;
                break;
            case 2:
                type = NetState.CDMA.type;
                break;
            case 3:
                type = NetState.UMTS.type;
                break;
            case 4:
                type = NetState.LTE.type;
                break;
            case 5:
            default:
                type = NetState.UNKOWN.type;
                break;
        }
        return type;

    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取经纬度
     *
     * @param context
     * @return
     */
    public static String getLngAndLat(Context context) {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  //从gps获取经纬度
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {//当GPS信号弱没获取到位置的时候又从网络获取
                return getLngAndLatWithNetwork(context);
            }
        } else {    //从网络获取经纬度
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
        return longitude + "," + latitude;
    }

    //从网络获取经纬度
    public static String getLngAndLatWithNetwork(Context context) {
        double latitude = 0.0;
        double longitude = 0.0;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        return longitude + "," + latitude;
    }


    public static LocationListener locationListener = new LocationListener() {

        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
        }
    };

    public static String getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗
        //从可用的位置提供器中，匹配以上标准的最佳提供器
        String locationProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Logger.e("经纬度未授权权限------------------0,0");
            return"0,0";
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);

        if (location != null) {
            //不为空,显示地理位置经纬度
            Logger.e("------------------"+location.getLatitude() + "," + location.getLongitude());
            return location.getLatitude() + "," + location.getLongitude();
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        Logger.e("经纬度未授权权限------------------0,0");
        return "0,0";
    }

    /**
     * 显示地理位置经度和纬度信息
     *
     * @param location
     */
    public static void showLocation(Location location) {
        Logger.e(location.getLatitude() + "," + location.getLongitude());

    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    public static LocationListener locationListener1 = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);

        }
    };
}
