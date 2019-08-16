package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Redis List API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-12 21:28
 */
public class RedisListClient<T extends RedisPoolConfig> extends BaseRedisClient<T> implements RedisListApi {

    public RedisListClient() {
        super();
    }

    @Override
    public void setPoolConfig(T poolConfig) {
        this.poolConfig = poolConfig;
    }

    @Override
    public Long listPush(String key, boolean fromRight, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return fromRight ? jedis.rpush(key, members) : jedis.lpush(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long listInsert(String key, boolean before, String pivot, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.linsert(key, before ? Client.LIST_POSITION.BEFORE : Client.LIST_POSITION.AFTER, pivot, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String listPop(String key, boolean fromRight) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return fromRight ? jedis.rpop(key) : jedis.lpop(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> listBlockPop(boolean fromRight, int timeout, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return fromRight ? jedis.brpop(timeout, keys) : jedis.blpop(timeout, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long listRemove(String key, int count, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.lrem(key, count, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String listTrim(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.ltrim(key, start, end);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> listRange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.lrange(key, start, end);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String listGetByIndex(String key, long index) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.lindex(key, index);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long listLen(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.llen(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String listSet(String key, long index, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.lset(key, index, member);
        } finally {
            T.releaseResource(jedis);
        }
    }
}