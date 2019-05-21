package com.mingrn.common.redis.client.base;

import com.mingrn.common.redis.config.AbstractJedisConfig;
import redis.clients.jedis.Jedis;

/**
 * Redis 基础接口共用抽象类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 03/10/2018 19:28
 */
public abstract class BaseJedisClient implements BaseJedisRepository {

	@Override
	public Long delete(String... keys) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.del(keys);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public String rename(String key, String newKey) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.rename(key, newKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long renameNx(String key, String newKey) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.renamenx(key, newKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long expireAt(String key, long timestamp) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.expireAt(key, timestamp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long pExpire(String key, long milliseconds) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.pexpire(key, milliseconds);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long pExpireAt(String key, long millisecondsTimestamp) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.pexpireAt(key, millisecondsTimestamp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long persist(String key) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.persist(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.ttl(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long pTtl(String key) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.pttl(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public Long exists(String... keys) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.exists(keys);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public String type(String key) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.type(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}

	@Override
	public String keyEncoding(String key) {
		Jedis jedis = null;
		try {
			jedis = AbstractJedisConfig.acquireResource();
			return jedis.objectEncoding(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			AbstractJedisConfig.releaseResource(jedis);
		}
	}
}
