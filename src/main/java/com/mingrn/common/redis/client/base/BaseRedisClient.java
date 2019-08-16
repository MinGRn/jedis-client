package com.mingrn.common.redis.client.base;

import com.mingrn.common.redis.config.AbstractPoolConfig;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 * Redis 基础接口共用抽象类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 03/10/2018 19:28
 */
public abstract class BaseRedisClient<T extends AbstractPoolConfig> implements BaseRedisApi {

    protected T poolConfig;

    public BaseRedisClient() {
    }

    public void setPoolConfig(T poolConfig) {
        this.poolConfig = poolConfig;
    }

    @Override
    public Long delete(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.del(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String rename(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.rename(key, newKey);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long renameAndNotExist(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.renamenx(key, newKey);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.expire(key, seconds);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtTimeStamp(String key, long timestamp) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.expireAt(key, timestamp);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long expireInMillis(String key, long milliseconds) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.pexpire(key, milliseconds);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtMillisTimeStamp(String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.pexpireAt(key, millisecondsTimestamp);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.persist(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.ttl(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long ttlInMillis(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.pttl(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long exists(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.exists(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.type(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String keyEncoding(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.objectEncoding(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.scan(cursor);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.scan(cursor, params);
        } finally {
            T.releaseResource(jedis);
        }
    }
}
