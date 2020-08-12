package com.mingrn.common.redis.distributed.lock;

import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Redis 锁机制
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 19/10/2018 09:57
 */
public class RedisLock {

    /** 连接池 */
    private final JedisPool jedisPool;
    /** 锁过期时间 */
    private final int expireInSecond;
    /** 锁轮询毫秒数 */
    private final long waitIntervalInMilliseconds;
    /** 锁等待超时毫秒数 */
    private final long tryLockTimeoutInMilliseconds;

    /** 锁过期时间1分钟 */
    private final static int EXPIRE_IN_SECOND = 5 * 60;
    /** 获取锁轮询间隔10毫秒 */
    private final static long WAIT_INTERVAL_IN_MILLISECONDS = 10;
    /** 锁等待时间5分钟 */
    private final static long TRY_LOCK_TIMEOUT_IN_MILLISECONDS = 5 * 60 * 1000;

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final String SECURITY_DEL_LUA_SCRIPT
            = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final Logger LOGGER = Logger.getLogger(RedisLock.class.getName());

    public RedisLock(final JedisPool jedisPool) {
        this(jedisPool, EXPIRE_IN_SECOND, WAIT_INTERVAL_IN_MILLISECONDS, TRY_LOCK_TIMEOUT_IN_MILLISECONDS);
    }

    /**
     * @param jedisPool                    Jedis 线程池
     * @param expireInSecond               过期时间
     * @param waitIntervalInMilliseconds   获取锁等待毫秒数
     * @param tryLockTimeoutInMilliseconds 获取锁超时时间
     */
    private RedisLock(final JedisPool jedisPool, final Integer expireInSecond, final Long waitIntervalInMilliseconds, final Long tryLockTimeoutInMilliseconds) {
        this.jedisPool = jedisPool;
        this.expireInSecond = expireInSecond;
        this.waitIntervalInMilliseconds = waitIntervalInMilliseconds;
        this.tryLockTimeoutInMilliseconds = tryLockTimeoutInMilliseconds;
    }

    /**
     * 获取分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST);
        } catch (JedisException e) {
            LOGGER.log(Level.WARNING, "Jedis Try Distributed Err", e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
        return LOCK_SUCCESS.equalsIgnoreCase(result);
    }

    /**
     * 获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        } catch (JedisException e) {
            LOGGER.log(Level.WARNING, "Jedis Try Distributed Err", e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
        return LOCK_SUCCESS.equalsIgnoreCase(result);
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        Object result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.eval(SECURITY_DEL_LUA_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        } catch (JedisException e) {
            LOGGER.log(Level.WARNING, "Jedis Release Distributed Err", e);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
        return RELEASE_SUCCESS.equals(result);
    }
}
