package com.ljcs.cxwl.util.MD5;

import java.security.MessageDigest;
import java.util.Locale;

/**
 * @author lijing
 * @desc 功能描述
 * @date 2016/7/26 15:26
 */
public class MD5Util {

    public static String encode(String str) {
        try {
            MessageDigest digestMessageDigest = MessageDigest.getInstance("MD5");
            digestMessageDigest.reset();
            digestMessageDigest.update(str.getBytes("UTF-8"));

            byte[] bs = digestMessageDigest.digest();

            String md5Str = md5To32(bs).toLowerCase(Locale.getDefault());

            return md5Str;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String md5To32(byte[] bs) {
        int i;
        StringBuffer sb1 = new StringBuffer();
        for (int offset = 0; offset < bs.length; offset++) {
            i = bs[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                sb1.append("0");
            }
            sb1.append(Integer.toHexString(i));
        }
        return sb1.toString();
    }

}
