package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis Hash API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/8/12 11:07
 */
public class RedisHashClient<T extends RedisPoolConfig> extends BaseRedisClient<T> implements RedisHashApi {

    public RedisHashClient() {
        super();
    }

    @Override
    public void setPoolConfig(T poolConfig) {
        this.poolConfig = poolConfig;
    }

    @Override
    public long hSet(String key, String field, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return binary ? jedis.hset(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8))
                    : jedis.hset(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean hManySet(String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = jedis.hmset(key, hash);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean hSetAndNotExist(String key, String field, String val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hsetnx(key, field, val) > 0;
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public long hIncrBy(String key, String field, long val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hincrBy(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public double hIncrByFloat(String key, String field, double val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hincrByFloat(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String hGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hget(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hgetAll(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Boolean hFieldExist(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hexists(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hManyGet(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hmget(key, fields);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long hLen(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hlen(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> hKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hkeys(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hVals(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hvals(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hscan(key, cursor);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hscan(key, cursor, params);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long hDel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hdel(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }
}
