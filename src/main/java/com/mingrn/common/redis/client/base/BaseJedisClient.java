package com.mingrn.common.redis.client.base;

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
public abstract class BaseJedisClient<T extends RedisPoolConfig> implements BaseJedisRepository {

    private T redisPoolConfig;

    public BaseJedisClient(T redisPoolConfig) {
        this.redisPoolConfig = redisPoolConfig;
    }

    @Override
    public Long delete(String... keys) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.del(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String rename(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.rename(key, newKey);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long renameAndNotExist(String key, String newKey) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.renamenx(key, newKey);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.expire(key, seconds);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtTimeStamp(String key, long timestamp) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.expireAt(key, timestamp);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireInMillis(String key, long milliseconds) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.pexpire(key, milliseconds);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long expireAtMillisTimeStamp(String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.pexpireAt(key, millisecondsTimestamp);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.persist(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.ttl(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long ttlInMillis(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.pttl(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long exists(String... keys) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.exists(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String type(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.type(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String keyEncoding(String key) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.objectEncoding(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.scan(cursor);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> scan(String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = redisPoolConfig.acquireResource();
            return jedis.scan(cursor, params);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }
}
