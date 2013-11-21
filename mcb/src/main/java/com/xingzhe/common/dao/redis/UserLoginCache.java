package com.xingzhe.common.dao.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.util.MD5Util;

@Repository
public class UserLoginCache {

	private static final Logger log = LoggerFactory.getLogger(UserLoginCache.class);

	private static Map<String, Object> map = new HashMap<String, Object>();

	// @Autowired
	// private RedisCache redisCache;

	/**
	 * 添加缓存
	 * 
	 * @param userName
	 * @param planFrom
	 * @param uuid
	 * @return
	 */
	public String putAcessToken(String userName, String planFrom, String uuid) {
		String acessToken = MD5Util.getInstance().md5s(
				(userName + planFrom + uuid).getBytes());
		// try {
		// //将token加入缓存
		// redisCache.putMap(userName + "_" + planFrom, uuid, acessToken);
		// } catch (Exception e) {
		// log.error(e.getMessage());
		// }
		map.put(userName + "_" + planFrom, acessToken);
		return acessToken;
	}

	/**
	 * 获取缓存
	 * 
	 * @param userName
	 * @param planFrom
	 * @param uuid
	 * @return
	 */
	public String getAcessToken(String userName, String planFrom, String uuid) {
		// try {
		// return redisCache.getMap(userName + "_" + planFrom, uuid);
		// } catch (Exception e) {
		// log.error(e.getMessage());
		// }
		return (String) map.get(userName + "_" + planFrom);
	}

	public void delAcessToken(String userName, String planFrom) {
		// try {
		// redisCache.remove(userName + "_" + planFrom);
		// } catch (Exception e) {
		// log.error(e.getMessage());
		// }
		map.remove(userName + "_" + planFrom);
	}

}
