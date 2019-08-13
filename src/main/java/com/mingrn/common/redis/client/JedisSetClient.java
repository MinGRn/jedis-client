package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis SET API
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-13 20:51
 */
public class JedisSetClient extends BaseJedisClient implements JedisSetRepository {

    private JedisSetClient(){}

    private static JedisSetClient INSTANCE = new JedisSetClient();

    private static JedisSetClient getInstance(){
        return INSTANCE;
    }

    @Override
    public Long setAdd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sadd(key, members);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long setRemove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.srem(key, members);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Boolean setIsMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sismember(key, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String setPop(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.spop(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setPop(String key, long count) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.spop(key, count);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setMembers(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.smembers(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long setMove(String source, String destination, String member) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.smove(source, destination, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setInter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sinter(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setUnion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sunion(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setDiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sdiff(keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long setInterAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sinterstore(destination, keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long setUnionAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sunionstore(destination, keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long setDiffAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.sdiffstore(destination, keys);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }
}