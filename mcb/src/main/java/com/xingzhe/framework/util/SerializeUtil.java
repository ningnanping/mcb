package com.xingzhe.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 类SerializeUtil.java的实现描述：序列化工具类
 * 
 * @author xuzhenhai 2012-11-29 下午3:07:09
 */
public class SerializeUtil {

    /**
     * 对象序列化为byte[]
     * 
     * @param object object
     * @return byte[]
     * @throws IOException
     */
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        byte[] bytes = bos.toByteArray();
        return bytes;

    }

    /**
     * 将byte[]反序列化成对象
     * 
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

}
