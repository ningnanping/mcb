package com.xingzhe.framework.test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class Test1 {
	protected Resource[] findAllClassPathResources(String location)
			throws IOException {
		String path = location;
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		Enumeration<?> resourceUrls = getClass().getClassLoader().getResources(
				path);
		Set<Resource> result = new LinkedHashSet<Resource>(16);
		while (resourceUrls.hasMoreElements()) {
			URL url = (URL) resourceUrls.nextElement();
			result.add(new UrlResource(url));
		}
		return result.toArray(new Resource[result.size()]);
	}

	@Test
	public void main() {
		String s = "classpath:com/xingzhe/*/resultmap/*-resultmap.xml";
		try {
			System.out.println(findAllClassPathResources(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
