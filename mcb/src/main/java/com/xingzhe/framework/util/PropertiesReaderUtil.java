package com.xingzhe.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesReaderUtil {
	protected static Logger logger = LoggerFactory.getLogger(PropertiesReaderUtil.class);

	private static Properties pro;

	public static void init(String file) {
		if (null == pro) {
			pro = new Properties();
		}
		InputStream is = null;
		try {
			is = new FileInputStream(PropertiesReaderUtil.class.getResource("/").getPath() + file);
			pro.load(is);
		} catch (IOException e) {
			logger.error("不能够加载" + file);
			// e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("不能够加载" + file);
					// e.printStackTrace();
				}
			}
		}
	}

	public static Properties getPro() {
		return pro;
	}
}
