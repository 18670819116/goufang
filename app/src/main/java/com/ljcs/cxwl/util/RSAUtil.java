package com.ljcs.cxwl.util;


import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author 胡智鹏 E-mail:
 * @version v1.0
 *          创建时间：2016-10-20 上午9:29:08
 *          类说明   rsa加密的类
 */

public class RSAUtil {
    public static final String RSA = "RSA";// 非对称加密密钥算法
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式
    public static final int DEFAULT_KEY_SIZE = 1024;//秘钥默认长度
    public static final byte[] DEFAULT_SPLIT = "#PART#".getBytes();    // 当要加密的内容超过bufferSize，则采用partSplit进行分块加密
    public static final int DEFAULT_BUFFERSIZE = (DEFAULT_KEY_SIZE / 8) - 11;// 当前秘钥支持加密的最大字节数
    private static final String RSA_PUBLICE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDl2qoaYKFH5a64rOtOb00KzTvY" +
            "/98YNbvSfjMNTiF//u7JZcm9/2HtV/hc1uwuGf0yRumVyPhfyjBgSz8E/f+JtnJK" +
            "3ZRf0iE39PN+jEuQ2qJjTz6wveB1i6xKm045/Q+N6xzhwCVQSM8IJQw5Rk91fxtT" + "hWUGGcwTJZ2HNdiuVwIDAQAB";
    private static final String ALGORITHM = "RSA";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * 得到公钥
     *
     * @param algorithm
     * @param bysKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String algorithm, String bysKey) throws NoSuchAlgorithmException,
            Exception {
        byte[] decodedKey = Base64.decode(bysKey, Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }


//    /**
//     * 用公钥对字符串进行加密
//     *
//     * @param data 原文
//     */
//    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
//        // 得到公钥
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
//        KeyFactory kf = KeyFactory.getInstance(RSA);
//        PublicKey keyPublic = kf.generatePublic(keySpec);
//        // 加密数据
//        Cipher cp = Cipher.getInstance(ECB_PKCS1_PADDING);
//        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
//        return cp.doFinal(data);
//    }

//    /**
//     * 使用公钥加密
//     * @param content
//     * @param key
//     * @return
//     */
//    public static String encryptByPublic(String content) {
//    	try {
//    		PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
//    		
//    		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//    		cipher.init(Cipher.ENCRYPT_MODE, pubkey);
//    		content.length();
//    		byte plaintext[] = content.getBytes("UTF-8");
//    		byte[] output = cipher.doFinal(plaintext);
//    		
////    		String s = new String(Base64.encode(output,Base64.DEFAULT));
//    		String s = new String(output);
//    		
//    		return s;
//    		
//    	} catch (Exception e) {
//    		return null;
//    	}
//    }

//    /**
//     * 使用公钥加密
//     * @param content
//     * @param key
//     * @return
//     */
//    public static byte[] encryptByPublic1(String content) {
//    	try {
//    		PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
//    		
//    		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//    		cipher.init(Cipher.ENCRYPT_MODE, pubkey);
//    		content.length();
//    		byte plaintext[] = content.getBytes("UTF-8");
//    		byte[] output = cipher.doFinal(plaintext);
//    		
////    		String s = new String(Base64.encode(output,Base64.DEFAULT));
////    		String s = new String(output);
//    		
//    		return output;
//    		
//    	} catch (Exception e) {
//    		return null;
//    	}
//    }

//    /**
//     * 用公钥对字符串进行分段加密
//     *
//     */
//    public static byte[] encryptByPublicKeyForSpilt(byte[] data) throws Exception {
//        int dataLen = data.length;
//        if (dataLen <= DEFAULT_BUFFERSIZE) {
//            return encryptByPublic(data.toString()).getBytes();
//        }
//        List<Byte> allBytes = new ArrayList<Byte>(1024);
//        int bufIndex = 0;
//        int subDataLoop = 0;
//        byte[] buf = new byte[DEFAULT_BUFFERSIZE];
//        for (int i = 0; i < dataLen; i++) {
//            buf[bufIndex] = data[i];
//            if (++bufIndex == DEFAULT_BUFFERSIZE || i == dataLen - 1) {
//                subDataLoop++;
//                if (subDataLoop != 1) {
//                    for (byte b : DEFAULT_SPLIT) {
//                        allBytes.add(b);
//                    }
//                }
//                byte[] encryptBytes = encryptByPublic(buf.toString()).getBytes();
//                for (byte b : encryptBytes) {
//                    allBytes.add(b);
//                }
//                bufIndex = 0;
//                if (i == dataLen - 1) {
//                    buf = null;
//                } else {
//                    buf = new byte[Math.min(DEFAULT_BUFFERSIZE, dataLen - i - 1)];
//                }
//            }
//        }
//        byte[] bytes = new byte[allBytes.size()];
//        {
//            int i = 0;
//            for (Byte b : allBytes) {
//                bytes[i++] = b.byteValue();
//            }
//        }
//        return bytes;
//    }
//    
//    /**
//     * 公钥将字符串进行分段加密
//     */
//    
//    public static String encryptByPublicKeyForSpilt(String data) {
//    	StringBuffer content = new StringBuffer();
//    	int dataLen = data.length();
//    	
//    	ByteArrayOutputStream out = new ByteArrayOutputStream();
//    	
//    	if (dataLen <= DEFAULT_BUFFERSIZE) {
//    		content.append(encryptByPublic(data));
//		} else {
//			List<String> datas = new ArrayList<>();
//			int temp = dataLen % DEFAULT_BUFFERSIZE == 0 ? dataLen / DEFAULT_BUFFERSIZE : dataLen / DEFAULT_BUFFERSIZE
// + 1;
//			for (int i = 0; i < temp; i++) {
//				if(((i + 1)*DEFAULT_BUFFERSIZE) < dataLen) {
//					datas.add(data.substring(i*DEFAULT_BUFFERSIZE, (i + 1)*DEFAULT_BUFFERSIZE));
//				} else {
//					datas.add(data.substring(i*DEFAULT_BUFFERSIZE, dataLen));
//				}
//			}
//			for (String string : datas) {
//				System.out.println("加密的字段为:" + string);
////				content.append(encryptByPublic(string));
//				byte[] b = encryptByPublic1(string);
//				out.write(b, 0, b.length);
//				System.out.println("字段加密的结果为:" + content);
//			}
//		}
//    	System.out.println("最后的结果为:" + content);
//    	
//		return new String(Base64.encode(out.toByteArray(), Base64.DEFAULT));
//    	
//    }

//    /**
//     * 用公钥对字符串进行加密
//     *
//     * @param data 原文
//     */
//    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
//        // 得到公钥
//    	PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
////        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
////        KeyFactory kf = KeyFactory.getInstance(RSA);
////        PublicKey keyPublic = kf.generatePublic(keySpec);
//        // 加密数据
//        Cipher cp = Cipher.getInstance(ECB_PKCS1_PADDING);
//        cp.init(Cipher.ENCRYPT_MODE, pubkey);
//        return cp.doFinal(data);
//    }

//    /**
//     * 用公钥对字符串进行分段加密
//     *
//     */
//    public static byte[] encryptByPublicKeyForSpilt(byte[] data) throws Exception {
//        int dataLen = data.length;
//        if (dataLen <= DEFAULT_BUFFERSIZE) {
//            return encryptByPublicKey(data);
//        }
//        List<Byte> allBytes = new ArrayList<Byte>(2048);
//        int bufIndex = 0;
//        int subDataLoop = 0;
//        byte[] buf = new byte[DEFAULT_BUFFERSIZE];
//        for (int i = 0; i < dataLen; i++) {
//            buf[bufIndex] = data[i];
//            if (++bufIndex == DEFAULT_BUFFERSIZE || i == dataLen - 1) {
//                subDataLoop++;
//                if (subDataLoop != 1) {
//                    for (byte b : DEFAULT_SPLIT) {
//                        allBytes.add(b);
//                    }
//                }
//                byte[] encryptBytes = encryptByPublicKey(buf);
//                for (byte b : encryptBytes) {
//                    allBytes.add(b);
//                }
//                bufIndex = 0;
//                if (i == dataLen - 1) {
//                    buf = null;
//                } else {
//                    buf = new byte[Math.min(DEFAULT_BUFFERSIZE, dataLen - i - 1)];
//                }
//            }
//        }
//        byte[] bytes = new byte[allBytes.size()];
//        {
//            int i = 0;
//            for (Byte b : allBytes) {
//                bytes[i++] = b.byteValue();
//            }
//        }
//        return bytes;
//    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
//        byte[] keyBytes = Base64.decodeBase64(publicKey);
//        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(ECB_PKCS1_PADDING);
//        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encode(encryptedData, Base64.DEFAULT);
    }

}