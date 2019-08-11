package com.mingrn.common.redis.client.base;

import com.mingrn.common.redis.config.JedisPoolConfig;
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
            jedis = JedisPoolConfig.acquireResource();
            return jedis.del(keys);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String rename(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.rename(key, newKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long renameAndNotExist(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.renamenx(key, newKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtTimeStamp(String key, long timestamp) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.expireAt(key, timestamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireInMillis(String key, long milliseconds) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.pexpire(key, milliseconds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtMillisTimeStamp(String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.pexpireAt(key, millisecondsTimestamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.persist(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long ttlInMillis(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.pttl(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long exists(String... keys) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.exists(keys);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.type(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String keyEncoding(String key) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolConfig.acquireResource();
            return jedis.objectEncoding(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JedisPoolConfig.releaseResource(jedis);
        }
    }
}
