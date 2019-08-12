package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

/**
 * Redis String API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-11 22:28
 */
public class JedisStringClient extends BaseJedisClient implements JedisStringRepository {

    private JedisStringClient() {
    }

    private static JedisStringClient INSTANCE = new JedisStringClient();

    public static JedisStringClient getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean set(String key, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = binary ? jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8)) : jedis.set(key, val);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean setAndNotExist(String key, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return (binary ? jedis.setnx(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8)) : jedis.setnx(key, val)) > 0;
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExistOrNot(String key, String val, boolean existOrNot, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx") :
                    jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8));
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = binary ? jedis.setex(key.getBytes(StandardCharsets.UTF_8), seconds, val.getBytes(StandardCharsets.UTF_8)) : jedis.setex(key, seconds, val);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary, boolean existOrNot) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx", "ex", seconds) :
                    jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8), "ex".getBytes(StandardCharsets.UTF_8), seconds);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtMillis(String key, String val, long millis, boolean binary, boolean existOrNot) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx", "px", millis)
                    : jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8), "px".getBytes(StandardCharsets.UTF_8), millis);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.get(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getWithBinaryKey(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.get(key.getBytes(StandardCharsets.UTF_8));
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String getAndSetNewVal(String key, String newVal) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.getSet(key, newVal);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getAndSetNewValWithBinary(String key, String newVal) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.getSet(key.getBytes(StandardCharsets.UTF_8), newVal.getBytes(StandardCharsets.UTF_8));
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getRangeWithBinary(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.getrange(key.getBytes(StandardCharsets.UTF_8), startOffset, endOffset);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }
}
