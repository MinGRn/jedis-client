package com.mingrn.common.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

/**
 * Redis 连接池配置
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/5/21 10:54
 */
public class JedisPoolConfig {

    private static JedisPool jedisPool;

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisPoolConfig.class);

    /** acquire redis resource */
    public static Jedis acquireResource() {
        if (jedisPool == null) {
            throw new JedisConnectionException("Can not Get Jedis Pool Resource, Please check whether the correct configuration!");
        }
        return jedisPool.getResource();
    }

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

    /** init redis connection */
    public void initConnection() {
        if (jedisPool == null) {
            throw new JedisConnectionException("Can't Connect Redis, Please check whether the connection configuration is correct again");
        }
        LOGGER.info("-----------------------------Redis Has been successfully connected-----------------------------");
    }

    /** destroy redis connection */
    public void destroyConnection() {
        LOGGER.info("-----------------------------Redis connection bean Has been successfully Destory-----------------------------");
    }

    //---------------------------------------------------Below is the constructor-------------------------------------------------------------


    public JedisPoolConfig() {
        this(Protocol.DEFAULT_HOST, Protocol.DEFAULT_PORT);
    }

    public JedisPoolConfig(final String host) {
        jedisPool = new JedisPool(host);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host) {
        this(poolConfig, host, Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisPoolConfig(String host, int port) {
        this(new GenericObjectPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
    }


    public JedisPoolConfig(final String host, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        jedisPool = new JedisPool(host, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final URI uri) {
        this(new GenericObjectPoolConfig(), uri, Protocol.DEFAULT_TIMEOUT);
    }

    public JedisPoolConfig(final URI uri, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        this(new GenericObjectPoolConfig(), uri, Protocol.DEFAULT_TIMEOUT, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final URI uri, final int timeout) {
        this(new GenericObjectPoolConfig(), uri, timeout);
    }

    public JedisPoolConfig(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        this(new GenericObjectPoolConfig(), uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout, final String password) {
        this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host,
                           int port, int timeout, final String password, final boolean ssl) {
        this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, null, ssl);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
                           final String password, final boolean ssl, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE,
                null, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, final int port) {
        this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, final int port, final boolean ssl) {
        this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, null, ssl);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, final int port, final boolean ssl,
                           final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE,
                null, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, final int port, final int timeout) {
        this(poolConfig, host, port, timeout, null, Protocol.DEFAULT_DATABASE, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host,
                           final int port, final int timeout, final boolean ssl) {
        this(poolConfig, host, port, timeout, null, Protocol.DEFAULT_DATABASE, null, ssl);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, final int port,
                           final int timeout, final boolean ssl, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
        this(poolConfig, host, port, timeout, null, Protocol.DEFAULT_DATABASE,
                null, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host,
                           int port, int timeout, final String password, final int database) {
        this(poolConfig, host, port, timeout, password, database, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           int timeout, final String password, final int database, final boolean ssl) {

        this(poolConfig, host, port, timeout, password, database, null, ssl);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           int timeout, final String password, final int database, final boolean ssl,
                           final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
                           final HostnameVerifier hostnameVerifier) {

        this(poolConfig, host, port, timeout, password, database, null, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           int timeout, final String password, final int database, final String clientName) {

        this(poolConfig, host, port, timeout, timeout, password, database, clientName, false,
                null, null, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           int timeout, final String password, final int database, final String clientName,
                           final boolean ssl) {

        this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl,
                null, null, null);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           int timeout, final String password, final int database, final String clientName,
                           final boolean ssl, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {

        this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final String host, int port,
                           final int connectionTimeout, final int soTimeout, final String password, final int database,
                           final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {

        jedisPool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri) {
        this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri,
                           final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
                           final HostnameVerifier hostnameVerifier) {

        this(poolConfig, uri, Protocol.DEFAULT_TIMEOUT, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri, final int timeout) {

        this(poolConfig, uri, timeout, timeout);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri, final int timeout,
                           final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
                           final HostnameVerifier hostnameVerifier) {

        this(poolConfig, uri, timeout, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri,
                           final int connectionTimeout, final int soTimeout) {

        jedisPool = new JedisPool(poolConfig, uri, connectionTimeout, soTimeout);
    }

    public JedisPoolConfig(final GenericObjectPoolConfig poolConfig, final URI uri,
                           final int connectionTimeout, final int soTimeout, final SSLSocketFactory sslSocketFactory,
                           final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {

        jedisPool = new JedisPool(poolConfig, uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }
}