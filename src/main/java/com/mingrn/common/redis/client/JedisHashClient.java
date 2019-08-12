package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisClient;
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
public class JedisHashClient extends BaseJedisClient implements JedisHashRepository {

    private JedisHashClient() {
    }

    private static JedisHashClient INSTANCE = new JedisHashClient();

    private static JedisHashClient getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public long hSet(String key, String field, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return binary ? jedis.hset(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8))
                    : jedis.hset(key, field, val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean hManySet(String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            String isOk = jedis.hmset(key, hash);
            return "ok".equalsIgnoreCase(isOk);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public boolean hSetAndNotExist(String key, String field, String val) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hsetnx(key, field, val) > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public long hIncrBy(String key, String field, long val) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hincrBy(key, field, val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public double hIncrByFloat(String key, String field, double val) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hincrByFloat(key, field, val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public String hGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Boolean hFieldExist(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hexists(key, field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hManyGet(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long hLen(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hlen(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> hKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hkeys(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hVals(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hvals(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hscan(key, cursor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hscan(key, cursor, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long hDel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.hdel(key, field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }
}
