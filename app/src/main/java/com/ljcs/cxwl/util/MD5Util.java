package com.ljcs.cxwl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 胡智鹏 E-mail:
 * @version v1.0 创建时间：2016-10-20 上午9:03:02 
 * 类说明 md5加密的类
 */
public class MD5Util {
	public static Map<String, String> getStamp() {
		LinkedHashMap<String, String> stamp = new LinkedHashMap<String, String>();
		Calendar c = Calendar.getInstance();
		String timestamp = c.getTimeInMillis() /1000 + "";
		stamp.put("timestamp", timestamp);
		Random random = new Random();
		int noncestr = (int) (random.nextFloat() * 100000);
		stamp.put("noncestr", noncestr + "");
		String sign = getMD5(timestamp).substring(16,32) + getMD5(noncestr + "").substring(16,32);
//		System.out.println(getMD5(timestamp).substring(16,32));
//		System.out.println(getMD5(noncestr + "").substring(16,32));
		stamp.put("sign", sign);
		stamp.put("platform", "Android");
		stamp.put("app_version", "V2.3.4");
		return stamp;
	}

	public static String getMD5(String info) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(info.getBytes("UTF-8"));
			byte[] encryption = md5.digest();

			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < encryption.length; i++) {
				if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
					strBuf.append("0").append(
							Integer.toHexString(0xff & encryption[i]));
				} else {
					strBuf.append(Integer.toHexString(0xff & encryption[i]));
				}
			}

			return strBuf.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
