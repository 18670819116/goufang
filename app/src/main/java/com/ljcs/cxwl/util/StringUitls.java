package com.ljcs.cxwl.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 * 作者：yishangfei on 2016/12/31 0031 10:16
 * 邮箱：yishangfei@foxmail.com
 */
public class StringUitls {

	//MD5加密
	public static String getMD5(String info)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(info.getBytes("UTF-8"));
			byte[] encryption = md5.digest();

			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < encryption.length; i++)
			{
				if (Integer.toHexString(0xff & encryption[i]).length() == 1)
				{
					strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
				}
				else
				{
					strBuf.append(Integer.toHexString(0xff & encryption[i]));
				}
			}

			return strBuf.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			return "";
		}
		catch (UnsupportedEncodingException e)
		{
			return "";
		}
	}
    //判断String里面是否有中文
	public static final boolean isChineseCharacter(String chineseStr) {
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
				return true;
			}
		}
		return false;
	}

	public static String calculteDiffTime(long diff){

		long oneDay = 1000*60*60*24;
		long oneHour=1000*60*60;
		long oneMin = 1000*60;
		long oneSecond = 1000;

		StringBuilder sBuilder =new StringBuilder();
		long day = diff/oneDay;
		long hour = diff%oneDay/oneHour;
		long min = diff%oneDay%oneHour/oneMin ;
		long sen = diff%oneDay%oneHour%oneMin/oneSecond;

		if(day !=0){
			sBuilder.append(day).append("天");
		}
		if(day!=0 || hour !=0) {
			sBuilder.append(hour).append("时");
		}
		if(day!=0 || hour!=0 || min!=0){
			sBuilder.append(min).append("分");
		}
		sBuilder.append(sen).append("秒");
		return sBuilder.toString();
	}

	/**
	 * 只限输入中文和英文 10个字
	 * @param editText
	 */
	public static void setInputName(EditText editText) {
		InputFilter filter = new InputFilter() {
			Pattern pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】  <>《》]|[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

			@Override
			public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
				Matcher matcher = pattern.matcher(source);
				if (matcher.find()) return "";
				else return null;
			}
		};
		InputFilter lengthFilter = new InputFilter.LengthFilter(10);
		editText.setFilters(new InputFilter[]{filter, lengthFilter});
	}
}
