package com.mingrn.common.redis.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

/**
 * 公用连接类
 * 使用时, 应该使用子类继承该类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-16 15:33
 * @see RedisPoolConfig
 * @see RedisSentinelPoolConfig
 */
public abstract class AbstractPoolConfig extends Pool<Jedis> {

    JedisPool jedisPool;

    JedisSentinelPool jedisSentinelPool;

    /** acquire redis resource */
    public abstract Jedis acquireResource();

    /**
     * release redis resource
     *
     * @param jedis The redis instance
     */
    public static void releaseResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /** init */
    protected abstract void init();
}
