package com.cd.bbh.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Utils {
	/**
	 * 获取MD5加密后的字符串
	 * 
	 * @param str
	 *            明文
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String getMD5(String str) {
		/** 创建MD5加密对象 */
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5加密对象初始化失败。", e);
		}
		/** 进行加密 */
		md5.update(str.getBytes());
		/** 获取加密后的字节数组 */
		byte[] md5Bytes = md5.digest();
		String res = "";
		for (int i = 0; i < md5Bytes.length; i++) {
			int temp = md5Bytes[i] & 0xFF;
			if (temp <= 0XF) { // 转化成十六进制不够两位，前面加零
				res += "0";
			}
			res += Integer.toHexString(temp);
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getMD5("wapwap12"));
	}
}
