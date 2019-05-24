package com.mingrn.common.redis.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Jedis Pool 连接配置累
 *
 * <p> {@link #jedisPool} 资源没有做任何配置
 * 在使用时需要使用子类继承该类,并设置 {@code JedisPool} 资源.
 *
 * <p>下面是一个简单的示例,实际使用需要根据业务做适当扩展:
 *
 * <pre>{@code
 *   public class JedisConfig extends AbstractJedisConfig {
 *     static {
 *          try {
 *              Properties properties = PropertyUtil.loadProperties("redis.properties");
 *              port = properties.getProperty("redis.port", "6379");
 *              host = properties.getProperty("redis.host", "localhost");
 *
 *              jedisPool = new JedisPool(host, Integer.valueOf(port));
 *          } catch (Exception e) {
 *              throw new RuntimeException(e);
 *          }
 *     }
 *   }
 *
 *   public class SomeService {
 *       // rename geo key
 *       public String geoRename(String oldName, String newName){
 *           return JedisGeoClient.getInstance().rename(oldName, newName);
 *       }
 *   }
 * }</pre>
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/5/21 10:54
 */
public abstract class AbstractJedisConfig {

    protected static JedisPool jedisPool;

    protected static JedisSentinelPool jedisSentinelPool;

    /**
     * acquire redis resource
     */
    public static Jedis acquireResource() {
        if (jedisPool == null) {
            throw new JedisConnectionException("Can not Get Jedis-Pool Resource, Please check whether the correct configuration!");
        }
        return jedisPool.getResource();
    }

    /**
     * resource redis resource
     *
     * @param jedis The redis instance
     */
    public static void releaseResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * do action
     */
    protected abstract void run();


    /**
     * Properties Config Util
     */
    public static class PropertyUtil {

        /**
         * 加载property文件
         *
         * @param propertyFile config file name, e.g. redis.properties
         * @return {@link Properties}
         */
        public static Properties loadProperties(String propertyFile) {
            Properties properties = new Properties();
            try {
                InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile);
                if (is == null) {
                    is = PropertyUtil.class.getClassLoader().getResourceAsStream("properties/" + propertyFile);
                }
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties;
        }

        /**
         * Get Val By Key
         *
         * @param propertyFile 配置文件
         * @param key          键
         * @return key-val
         */
        public static String getValue(String propertyFile, String key) {
            Properties properties = loadProperties(propertyFile);
            return properties.getProperty(key);
        }
    }
}