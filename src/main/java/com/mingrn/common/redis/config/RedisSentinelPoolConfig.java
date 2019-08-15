package com.mingrn.common.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Redis Sentinel 连接池配置
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/8/15 17:43
 */
public class RedisSentinelPoolConfig {

    private String clientName;

    private Set<String> sentinels;

    private JedisSentinelPool jedisSentinelPool;

    private static final Logger LOGGER = Logger.getLogger(RedisPoolConfig.class.getName());

    /** acquire sentinel resource */
    public Jedis acquireResource() {
        if (jedisSentinelPool == null) {
            throw new JedisConnectionException("Can not Get Redis Sentinel Pool Resource, Please check whether the correct configuration!");
        }
        jedisSentinelPool.getCurrentHostMaster();
        return jedisSentinelPool.getResource();
    }

    /**
     * release redis resource
     *
     * @param jedisSentinelPool The redis sentinel instance
     */
    public static void releaseResource(JedisSentinelPool jedisSentinelPool) {
        if (jedisSentinelPool != null) {
            jedisSentinelPool.destroy();
        }
    }

    /** init sentinel connection */
    private void init() {
        if (jedisSentinelPool == null) {
            throw new JedisConnectionException("Can't Connect Redis Sentinel, Please check whether the connection configuration is correct again");
        }
        LOGGER.info("-----------------------------Redis Sentinel [clientName: " + clientName + ", Nodes: " + sentinels.toString() + "] Has Been Successfully Connected-----------------------------");
    }

    /** destroy sentinel connection */
    private void destroy() {
        LOGGER.info("-----------------------------Redis Sentinel [clientName: " + clientName + ", Nodes: " + sentinels.toString() + "] Connection Has Been Successfully Destroy-----------------------------");
    }

    //---------------------------------------------------Below Is The Constructor-------------------------------------------------------------
    //---------------------------------------You Can Configure These Attributes When Injection Bean-------------------------------------------

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels, String password) {
        this(masterName, sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, password);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, final String password) {
        this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, password);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig, int timeout, final String password) {
        this(masterName, sentinels, poolConfig, timeout, password, Protocol.DEFAULT_DATABASE);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels, final GenericObjectPoolConfig poolConfig) {
        this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels) {
        this(masterName, sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels,
                                   final GenericObjectPoolConfig poolConfig, final int timeout) {
        this(masterName, sentinels, poolConfig, timeout, null, Protocol.DEFAULT_DATABASE);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels,
                                   final GenericObjectPoolConfig poolConfig,
                                   int timeout, final String password, final int database) {
        this(masterName, sentinels, poolConfig, timeout, timeout, password, database);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels,
                                   final GenericObjectPoolConfig poolConfig, int timeout,
                                   final String password, final int database, final String clientName) {
        this(masterName, sentinels, poolConfig, timeout, timeout, password, database, clientName);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels,
                                   final GenericObjectPoolConfig poolConfig, final int timeout,
                                   final int soTimeout, final String password, final int database) {
        this(masterName, sentinels, poolConfig, timeout, soTimeout, password, database, null);
    }

    public RedisSentinelPoolConfig(String masterName, Set<String> sentinels,
                                   final GenericObjectPoolConfig poolConfig, final int connectionTimeout,
                                   final int soTimeout, final String password, final int database, final String clientName) {
        this.clientName = clientName;
        this.sentinels = sentinels;
        this.jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig, connectionTimeout, soTimeout, password, database, clientName);
    }
}
