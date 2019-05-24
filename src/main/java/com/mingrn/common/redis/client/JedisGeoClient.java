package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisClient;
import com.mingrn.common.redis.config.AbstractJedisConfig;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * Redis GEO API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 03/10/2018 20:49
 */
public class JedisGeoClient extends BaseJedisClient implements JedisGeoRepository {

    private JedisGeoClient() {
    }

    private static final JedisGeoRepository INSTANCE = new JedisGeoClient();

    public static JedisGeoRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Long geoAdd(String key, Double longitude, Double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geoadd(key, longitude, latitude, member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public GeoCoordinate geoPos(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            List<GeoCoordinate> geopos = jedis.geopos(key, member);
            if (geopos.size() > 0) {
                return geopos.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoCoordinate> geoPos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geopos(key, members);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geodist(key, member1, member2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geodist(key, member1, member2, unit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.georadius(key, longitude, latitude, radius, unit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam withParam) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.georadius(key, longitude, latitude, radius, unit, withParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.georadiusByMember(key, member, radius, unit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam withParam) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.georadiusByMember(key, member, radius, unit, withParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }

    @Override
    public List<String> geoHash(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = AbstractJedisConfig.acquireResource();
            return jedis.geohash(key, members);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            AbstractJedisConfig.releaseResource(jedis);
        }
    }
}