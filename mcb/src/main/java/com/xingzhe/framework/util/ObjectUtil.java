package com.xingzhe.framework.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

/**
 * 类ObjectUtil.java的实现描述：对象工具类
 * 
 * @author xuzhenhai 2012-11-29 下午1:53:06
 */
public class ObjectUtil {

    /**
     * 将对象序列化成字符串
     * 
     * @param obj 待转对象
     * @return String
     * @throws IOException
     */
    public static String objectToHexString(Object obj) throws IOException {
        return bytesToHexString(SerializeUtil.serialize(obj));
    }

    /**
     * 将16进制字符串转化成对象
     * 
     * @param hexString 十六进制字符串(先前转的字符串)
     * @return Object
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Object hexStringToObject(String hexString) throws IOException, ClassNotFoundException {
        return SerializeUtil.unserialize(hexStringToBytes(hexString));
    }

    /**
     * byte[]转十六进制String
     * 
     * @param bytes bytes
     * @return String
     */
    private static String bytesToHexString(byte[] bytes) {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        StringBuilder sbulider = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                // 长度不够补零，方便后续逆运算
                sbulider.append(0);
            }
            sbulider.append(hex);
        }
        return sbulider.toString();
    }

    /**
     * 十六进制String转byte[]
     * 
     * @param hexString 十六进制String
     * @return byte[]
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isBlank(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        // 该方法是bytesToHexString的逆运算,在bytesToHexString中保证hexString的length为偶数
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (hexCharToByte(hexChars[pos]) << 4 | hexCharToByte(hexChars[pos + 1]));
        }
        return bytes;
    }

    /**
     * 十六进制char转十进制byte
     * 
     * @param hexChar 十六进制char
     * @return byte
     */
    private static byte hexCharToByte(char hexChar) {
        return (byte) "0123456789ABCDEF".indexOf(hexChar);
    }

}
