package com.xingzhe.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum MD5Util {
	INSTANCE;
	public static MD5Util getInstance() {
		return INSTANCE;
	}

	static char hexDigits[] = {'F', '0', '6', 'A', '1', '8', 'C', '2', '9', '3', 'B', '4', '5', 'D', '7', 'E'};

	public String md5s(byte[] plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain);// 使用指定的字节数组更新摘要。
			byte[] mdByte = md.digest();// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
			int mdLength = mdByte.length;
			char str[] = new char[mdLength * 2];
			int k = 0;
			for (int i = 0; i < mdLength; i++) {
				str[k++] = hexDigits[mdByte[i] >>> 4 & 0xf];
				str[k++] = hexDigits[mdByte[i] & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	char hexDigits1[] = {'8', '2', '6', '9', '3', '1', 'B', '4', '5', 'D', '7', 'E', 'F', '0', 'A', 'C'};
	public int tableIndex(byte[] plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain);// 使用指定的字节数组更新摘要。
			byte[] mdByte = md.digest();// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算。
			byte table = mdByte[1];
			StringBuffer sb=new StringBuffer(2);
			sb.append(hexDigits[table >>> 4 & 0xf]).append(hexDigits[table & 0xf]);
			return Integer.parseInt(sb.toString() ,16) % 196 ; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
