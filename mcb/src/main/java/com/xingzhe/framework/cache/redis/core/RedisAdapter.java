package com.xingzhe.framework.cache.redis.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.Transaction;

import com.xingzhe.framework.cache.redis.RedisCache;
import com.xingzhe.framework.util.HessianUtil;

/**
 * 类RedisAdapter.java的实现描述：Redis client实现
 * 
 */
@Repository
public class RedisAdapter implements RedisCache {

	private static final Logger logger = Logger.getLogger(RedisAdapter.class);

	public static final long ONE_MILLI_NANOS = 1000000L;

	public static final Random random = new Random();
	/**
	 * template
	 */
	@Autowired
	RedisTemplate<Serializable, Serializable> redisTemplate;

	public RedisAdapter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.BaseCache#put(java.lang.String,
	 * java.io.Serializable)
	 */
	public boolean put(final String key, final Serializable value) {
		String statusCode = (String) redisTemplate
				.execute(new RedisCallback<Serializable>() {

					public Serializable doInRedis(ShardedJedis jedis) {
						try {
							return jedis.set(key.getBytes(),
									HessianUtil.serialize(value));
						} catch (IOException e) {
							logger.error("serialize error.[key:" + key + "]", e);
							return null;
						}

					}
				});
		if (statusCode == null) {
			return false;
		}
		return true;
	}

	public boolean putNx(final String key, final Serializable value,
			final int expTime) {
		Long statusCode = (Long) redisTemplate
				.execute(new RedisCallback<Serializable>() {

					public Serializable doInRedis(ShardedJedis jedis) {
						try {
							Long code = jedis.setnx(key.getBytes(),
									HessianUtil.serialize(value));
							jedis.expire(key, expTime);
							return code;
						} catch (IOException e) {
							logger.error("serialize error.[key:" + key + "]", e);
							return null;
						}
					}
				});
		if (statusCode == null) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.redis.RedisCache#putNx(java.lang.String,
	 * java.io.Serializable)
	 */
	public boolean putNx(final String key, final Serializable value) {
		Long statusCode = (Long) redisTemplate
				.execute(new RedisCallback<Serializable>() {

					public Serializable doInRedis(ShardedJedis jedis) {
						try {
							return jedis.setnx(key.getBytes(),
									HessianUtil.serialize(value));
						} catch (IOException e) {
							logger.error("serialize error.[key:" + key + "]", e);
							return null;
						}

					}
				});
		if (statusCode == null) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.BaseCache#put(java.lang.String,
	 * java.io.Serializable, int)
	 */
	public boolean put(final String key, final Serializable value,
			final int expirationTime) {
		String statusCode = (String) redisTemplate
				.execute(new RedisCallback<Serializable>() {

					public Serializable doInRedis(ShardedJedis jedis) {
						try {
							// SETNX 如果已经存在就执行操作。
							return jedis.setex(key.getBytes(), expirationTime,
									HessianUtil.serialize(value));
						} catch (IOException e) {
							logger.error("serialize error.[key:" + key + "]", e);
							return null;
						}

					}
				});
		return responseStatusCode(statusCode);
	}

	/**
	 * 缓存一个Map到Cache中，支持锁。
	 * 
	 * @param key
	 *            key
	 * @param map
	 *            HMSET
	 * @param expTime
	 *            单位:seconds
	 * @return
	 */
	public boolean putMap(final String key,
			final Map<String, Serializable> map, final int expTime) {

		String statusCode = redisTemplate.execute(new RedisCallback<String>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public String doInRedis(ShardedJedis jedis) {
				try {
					// long nano = System.nanoTime();
					// long timeout = 100 * ONE_MILLI_NANOS;
					// while ((System.nanoTime() - nano) < timeout) {
					// if (jedis.getShard(key).watch(key)){
					//
					// }
					// Transaction t = jedis.getShard(key).multi();
					// 开启事务，当server端收到multi指令
					// 会将该client的命令放入一个队列，然后依次执行，知道收到exec指令
					// t.getSet(key, LOCKED);
					// t.expire(key, EXPIRE);
					String code = jedis.hmset(key.getBytes(),
							HessianUtil.serialize(map));
					jedis.expire(key, expTime);
					// String ret = (String) t.exec().get(0);
					// if (ret == null || ret.equals("UNLOCK")) {
					// return code;
					// }
					// 短暂休眠，nano避免出现活锁
					// Thread.sleep(3, r.nextInt(500));
					return code;

				} catch (IOException e) {
					logger.error("serialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});

		return responseStatusCode(statusCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.redis.RedisCache#putMap(java.lang.String,
	 * java.util.Map)
	 */
	public boolean putMap(final String key, final Map<String, Serializable> map) {
		String statusCode = redisTemplate.execute(new RedisCallback<String>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public String doInRedis(ShardedJedis jedis) {
				try {
					String code = jedis.hmset(key.getBytes(),
							HessianUtil.serialize(map));
					return code;

				} catch (IOException e) {
					logger.error("serialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});

		return responseStatusCode(statusCode);
	}

	/**
	 * 添加，或者一个已存在的key 的field.
	 * 
	 * @param key
	 *            Hash表的key.
	 * @param field
	 *            字段的key
	 * @param value
	 *            每个字段的值
	 * @return
	 */
	public boolean putMap(final String key, final String field,
			final Serializable value) {
		Long code = redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(ShardedJedis jedis) {
				try {
					return jedis.hset(key.getBytes(), field.getBytes(),
							HessianUtil.serialize(value));
				} catch (IOException e) {
					logger.error("serialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});

		if (code == null) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.redis.RedisCache#putMap(java.util.Map, int)
	 */
	public List<Object> putMap(final Map<String, Serializable> map,
			final int expTime) {

		if (map == null || map.isEmpty()) {
			throw new NullPointerException("map is null.");
		}

		String statusCode = redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(ShardedJedis jedis) {
				try {

					return jedis.getShard(
							map.keySet().toArray(new String[] {})[0]).mset(
							HessianUtil.mapToByteArray(map));
				} catch (IOException e) {
					logger.error("serialize error.[map:" + map + "]", e);
					return null;
				}
			}
		});

		if (!responseStatusCode(statusCode)) {
			throw new RuntimeException("The batch add value fails:[statusCode:"
					+ statusCode + ", map:" + map + "]");
		}

		List<Object> result = executePiplineCommand(new RedisPiplineCallBack<List<Object>>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisPiplineCallBack#doInRedis
			 * (redis.clients.jedis.ShardedJedisPipeline )
			 */
			public List<Object> doInRedis(ShardedJedisPipeline jedis) {
				for (Map.Entry<String, Serializable> entry : map.entrySet()) {
					jedis.expire(entry.getKey(), expTime);
				}

				return jedis.syncAndReturnAll();

			}
		});

		return result;
	}

	/**
	 * HGETALL key返回哈希表key中，所有的域和值。
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, Serializable> getMapAll(final String key) {
		return redisTemplate
				.execute(new RedisCallback<Map<String, Serializable>>() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis
					 * (redis.clients.jedis.ShardedJedis)
					 */
					public Map<String, Serializable> doInRedis(
							ShardedJedis jedis) {
						Map<byte[], byte[]> rmap = jedis.hgetAll(key.getBytes());
						try {
							if (rmap == null || rmap.size() == 0) {
								return null;
							}
							return HessianUtil.deserialize(rmap);
						} catch (IOException e) {
							logger.error("deserialize error.[key:" + key + "]",
									e);
							return null;
						}
					}
				});

	}

	/**
	 * HMGET key field [field ...]返回哈希表key中，一个或多个给定域的值。<br/>
	 * 注：如果某个field没有，则按照顺序返回null.例：[value, null, null]
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> getMap(final String key, final String... fields) {

		return redisTemplate.execute(new RedisCallback<List<Object>>() {

			public List<Object> doInRedis(ShardedJedis jedis) {
				List<byte[]> rlist = jedis.hmget(key.getBytes(),
						HessianUtil.strArrayToByteArray(fields));

				try {
					if (CollectionUtils.isEmpty(rlist)) {
						return null;
					}
					return HessianUtil.deserialize(rlist);
				} catch (IOException e) {
					logger.error("deserialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});
	}

	/**
	 * 根据key,field查询 Map中的值。
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public <V extends Serializable> V getMap(final String key,
			final String field) {
		return redisTemplate.execute(new RedisCallback<V>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			@SuppressWarnings("unchecked")
			public V doInRedis(ShardedJedis jedis) {

				try {
					byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
					if (ArrayUtils.isEmpty(bytes)) {
						return null;
					}
					return (V) HessianUtil.deserialize(bytes);
				} catch (IOException e) {
					logger.error("deserialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.redis.RedisCache#getList(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getList(final String... keys) {
		return redisTemplate.execute(new RedisCallback<List<Object>>() {

			public List<Object> doInRedis(ShardedJedis jedis) {

				List<byte[]> list = jedis.getShard(keys[0]).mget(
						HessianUtil.strArrayToByteArray(keys));
				try {
					if (CollectionUtils.isEmpty(list)) {
						return null;

					}
					return HessianUtil.deserialize(list);
				} catch (IOException e) {
					logger.error("deserialize error.[key:" + keys + "]", e);
					return null;
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.BaseCache#get(java.lang.String)
	 */
	public <V extends Serializable> V get(final String key) {
		return redisTemplate.execute(new RedisCallback<V>() {

			@SuppressWarnings("unchecked")
			public V doInRedis(ShardedJedis jedis) {
				try {
					byte[] value = jedis.get(key.getBytes());
					if (value == null) {
						return null;
					}
					return (V) HessianUtil.deserialize(value);
				} catch (IOException e) {
					logger.error("deserialize error.[key:" + key + "]", e);
					return null;
				}
			}

		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.BaseCache#remove(java.lang.String)
	 */
	public void remove(final String key) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			public Serializable doInRedis(ShardedJedis jedis) {
				return jedis.del(key);
			}

		});

	}

	/**
	 * HDEL key field [field ...]删除哈希表key中的一个或多个指定域。
	 * 
	 * @param key
	 * @param field
	 */
	public void remove(final String key, final String... fields) {
		redisTemplate.execute(new RedisCallback<Serializable>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public Serializable doInRedis(ShardedJedis jedis) {
				return jedis.hdel(key, fields);
			}
		});
	}

	/**
	 * EXISTS 检查给定key是否存在。
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public Boolean doInRedis(ShardedJedis jedis) {

				return jedis.exists(key);
			}
		});
	}

	/**
	 * HEXISTS key field查看哈希表key中，给定域field是否存在。
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Boolean exists(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public Boolean doInRedis(ShardedJedis jedis) {

				return jedis.hexists(key, field);
			}
		});
	}

	public <V> V executePiplineCommand(final RedisPiplineCallBack<V> callBack) {
		return redisTemplate.execute(new RedisCallback<V>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public V doInRedis(ShardedJedis jedis) {
				return callBack.doInRedis(jedis.pipelined());
			}
		});
	}

	/**
	 * 采用循环和sleep,watch,multi这些方法可以保证分布式情况下的同步.
	 * </br>方法加上synchronized是为了避免一个线程在watch到一个key后
	 * ,multi后执行事务时.其他线程也来watch这个key导致异常 </br> 这个异常是:ERR WATCH inside MULTI is
	 * not allowed
	 */
	public synchronized <V> V executeTransactionCommand(final String key,
			final RedisTransactionCallBack<V> callBack) {
		return redisTemplate.execute(new RedisCallback<V>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public V doInRedis(ShardedJedis shardedJedis) {
				// 循环5次失败，就退出。
				int i = 0;
				while (i < 1000) {
					Jedis jedis = shardedJedis.getShard(key.getBytes());
					if (StringUtils.equals(jedis.watch(key), "OK")) {
						if (logger.isInfoEnabled()) {
							logger.info("watch is key:[" + key + "]");
						}
					}

					Transaction transaction = jedis.multi();
					V v = callBack.doTransaction(transaction);
					/*
					 * if (transaction.exec() != null) { return v; }
					 */
					if (v != null) {
						jedis.unwatch();
						return v;
					}

					logger.warn("The key modified by other threads, retrying. key:["
							+ key + "]");

					jedis.unwatch();

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						logger.error(e);
					}
					i++;
				}
				return null;
			}
		});
	}

	/**
	 * 返回执行redis的响应码.
	 * 
	 * @param statusCode
	 * @return
	 */
	private final boolean responseStatusCode(String statusCode) {
		if (StringUtils.equals("OK", statusCode)) {
			return true;
		}

		logger.error("Operation redis failed, error code:[" + statusCode + "]");
		return false;
	}

	/**
	 * @param redisTemplate
	 *            the redisTemplate to set
	 */
	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.as.common.cache.redis.RedisCache#publish(java.lang.String,
	 * java.lang.String)
	 */
	public Long publish(final String channel, final String message) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public Long doInRedis(ShardedJedis jedis) {

				return jedis.getShard(channel).publish(channel, message);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.as.common.cache.redis.RedisCache#subscribe(redis.clients.jedis
	 * .JedisPubSub, java.lang.String)
	 */
	public Boolean subscribe(final JedisPubSub listner, final String channel) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jd.as.common.cache.redis.core.RedisCallback#doInRedis(redis
			 * .clients.jedis.ShardedJedis)
			 */
			public Boolean doInRedis(ShardedJedis jedis) {

				jedis.getShard(channel).subscribe(listner, channel);
				return true;
			}
		});
	}

	public void close() {
		redisTemplate.destroy();
	}

	public boolean zadd(final String key, final String score,
			final Serializable value) {
		Long statusCode = redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(ShardedJedis jedis) {
				try {
					return jedis.zadd(key.getBytes(),
							Double.parseDouble(score),
							HessianUtil.serialize(value));
				} catch (Exception e) {
					logger.error("serialize error.[key:" + key + "]", e);
					return null;
				}
			}

		});
		if (statusCode == null) {
			return false;
		}
		return true;
	}

	public Long zaddMap(final String key,
			final Map<String, Serializable> scoreMembers, final int expTime) {
		Long statusCode = redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(ShardedJedis jedis) {
				try {
					Long res = jedis.zadd(key.getBytes(),
							HessianUtil.serializeZ(scoreMembers));
					jedis.expire(key, expTime);
					return res;
				} catch (IOException e) {
					logger.error("serialize error.[key:" + key + "]", e);
					return null;
				}
			}

		});
		return statusCode;
	}

	public void zremByScore(final String key, final String score) {
		redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key.getBytes(),
						Double.parseDouble(score), Double.parseDouble(score));
			}

		});
	}

	public void zremByScores(final String key, final String... scores) {
		executePiplineCommand(new RedisPiplineCallBack<List<Object>>() {

			public List<Object> doInRedis(ShardedJedisPipeline jedis) {
				for (int i = 0, t = scores.length; i < t; i++) {
					jedis.zremrangeByScore(key, Double.parseDouble(scores[i]),
							Double.parseDouble(scores[i]));
				}
				return jedis.syncAndReturnAll();
			}

		});

	}

	public void zremrangeByScore(final String key, final Double start,
			final Double end) {
		redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(ShardedJedis jedis) {
				return jedis.zremrangeByScore(key.getBytes(), start, end);
			}
		});
	}

	public <V extends Serializable> V getSet(final String key,
			final String score) {
		return redisTemplate.execute(new RedisCallback<V>() {

			@SuppressWarnings("unchecked")
			public V doInRedis(ShardedJedis jedis) {
				try {
					Set<byte[]> bytes = jedis.zrangeByScore(key.getBytes(),
							Double.parseDouble(score),
							Double.parseDouble(score));
					if (CollectionUtils.isEmpty(bytes)) {
						return null;
					}
					return (V) HessianUtil.deserializeZ(bytes).get(0);
				} catch (IOException e) {
					logger.error("deserialize error.[key:" + key + "]", e);
					return null;
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public   List<Object> getSets(final String key,
			final int start, final int end) {
		return redisTemplate.execute(new RedisCallback<List<Object>>() {

			public List<Object> doInRedis(ShardedJedis jedis) {
				try {
					return HessianUtil.deserializeZ(jedis.zrange(
							key.getBytes(), start, end));
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}

		});
	}

	public Long countSet(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(ShardedJedis jedis) {
				return jedis.zcard(key.getBytes());
			}
		});
	}

}
