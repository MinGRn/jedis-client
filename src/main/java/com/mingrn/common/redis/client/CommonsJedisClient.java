package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;
import com.mingrn.common.redis.client.base.BaseRedisClient;
import com.mingrn.common.redis.config.AbstractPoolConfig;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 常用 API
 * String, Hash, List, Set, SortSet
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-16 15:42
 */
public class CommonsJedisClient<T extends AbstractPoolConfig> extends BaseRedisClient<T>
        implements BaseRedisApi, RedisStringApi, RedisGeoApi, RedisHashApi, RedisSetApi, RedisSortSetApi {

    public CommonsJedisClient() {
        super();
    }

    @Override
    public void setPoolConfig(T poolConfig) {
        this.poolConfig = poolConfig;
    }

    @Override
    public boolean set(String key, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = binary ? jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8)) : jedis.set(key, val);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean setAndNotExist(String key, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return (binary ? jedis.setnx(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8)) : jedis.setnx(key, val)) > 0;
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExistOrNot(String key, String val, boolean existOrNot, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx") :
                    jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8));
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = binary ? jedis.setex(key.getBytes(StandardCharsets.UTF_8), seconds, val.getBytes(StandardCharsets.UTF_8)) : jedis.setex(key, seconds, val);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary, boolean existOrNot) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx", "ex", seconds) :
                    jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8), "ex".getBytes(StandardCharsets.UTF_8), seconds);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean setExpireAtMillis(String key, String val, long millis, boolean binary, boolean existOrNot) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = !binary ? jedis.set(key, val, existOrNot ? "xx" : "nx", "px", millis)
                    : jedis.set(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8), (existOrNot ? "xx" : "nx").getBytes(StandardCharsets.UTF_8), "px".getBytes(StandardCharsets.UTF_8), millis);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.get(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getWithBinaryKey(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.get(key.getBytes(StandardCharsets.UTF_8));
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String getAndSetNewVal(String key, String newVal) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.getSet(key, newVal);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getAndSetNewValWithBinary(String key, String newVal) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.getSet(key.getBytes(StandardCharsets.UTF_8), newVal.getBytes(StandardCharsets.UTF_8));
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.getrange(key, startOffset, endOffset);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public byte[] getRangeWithBinary(String key, long startOffset, long endOffset) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.getrange(key.getBytes(StandardCharsets.UTF_8), startOffset, endOffset);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long geoAdd(String key, Double longitude, Double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geoadd(key, longitude, latitude, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public GeoCoordinate geoPos(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            List<GeoCoordinate> geoPos = jedis.geopos(key, member);
            return geoPos.size() > 0 ? geoPos.get(0) : null;
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoCoordinate> geoPos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geopos(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geodist(key, member1, member2);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geodist(key, member1, member2, unit);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.georadius(key, longitude, latitude, radius, unit);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam withParam) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.georadius(key, longitude, latitude, radius, unit, withParam);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.georadiusByMember(key, member, radius, unit);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam withParam) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.georadiusByMember(key, member, radius, unit, withParam);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> geoHash(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.geohash(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> geoMembers(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public long hSet(String key, String field, String val, boolean binary) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return binary ? jedis.hset(key.getBytes(StandardCharsets.UTF_8), field.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8))
                    : jedis.hset(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean hManySet(String key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            String isOk = jedis.hmset(key, hash);
            return "ok".equalsIgnoreCase(isOk);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public boolean hSetAndNotExist(String key, String field, String val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hsetnx(key, field, val) > 0;
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public long hIncrBy(String key, String field, long val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hincrBy(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public double hIncrByFloat(String key, String field, double val) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hincrByFloat(key, field, val);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String hGet(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hget(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hgetAll(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Boolean hFieldExist(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hexists(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hManyGet(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hmget(key, fields);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long hLen(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hlen(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> hKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hkeys(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public List<String> hVals(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hvals(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hscan(key, cursor);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hscan(key, cursor, params);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long hDel(String key, String... field) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.hdel(key, field);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setAdd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sadd(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setRemove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.srem(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Boolean setIsMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sismember(key, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String setPop(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.spop(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setPop(String key, long count) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.spop(key, count);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setMembers(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.smembers(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setMove(String source, String destination, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.smove(source, destination, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setInter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sinter(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setUnion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sunion(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setDiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sdiff(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setInterAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sinterstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setUnionAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sunionstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setDiffAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sdiffstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> setScan(String key, String cursor) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sscan(key, cursor);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<String> setScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sscan(key, cursor, params);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetAdd(String key, String member, double score) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zadd(key, score, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetAdd(String key, String member, double score, ZAddParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zadd(key, score, member, params);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetCard(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zcard(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Double sortSetScore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zscore(key, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetRank(String key, String member, boolean reverse) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reverse ? jedis.zrevrank(key, member) : jedis.zrank(key, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetRemove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zrem(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Double sortSetScoreIncrBy(String key, String member, double score) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zincrby(key, score, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> sortSetRange(String key, long minRank, long maxRank, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reversed ? jedis.zrevrange(key, minRank, maxRank) : jedis.zrange(key, minRank, maxRank);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> sortSetRangeByScore(String key, double minScore, double maxScore, boolean reversed) {
        return sortSetRangeByScore(key, minScore + "", maxScore + "", reversed);
    }

    @Override
    public Set<String> sortSetRangeByScore(String key, String minScore, String maxScore, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reversed ? jedis.zrevrangeByScore(key, maxScore, minScore) : jedis.zrangeByScore(key, minScore, maxScore);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> sortSetRangeByScore(String key, double minScore, double maxScore, boolean reversed, int offset, int count) {
        return sortSetRangeByScore(key, minScore + "", maxScore + "", reversed, offset, count);
    }

    @Override
    public Set<String> sortSetRangeByScore(String key, String minScore, String maxScore, boolean reversed, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reversed ? jedis.zrevrangeByScore(key, maxScore, minScore, offset, count)
                    : jedis.zrangeByScore(key, minScore, maxScore, offset, count);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<Tuple> sortSetRangeByScoreWithScores(String key, double minScore, double maxScore, boolean reversed) {
        return sortSetRangeByScoreWithScores(key, minScore + "", maxScore + "", reversed);
    }

    @Override
    public Set<Tuple> sortSetRangeByScoreWithScores(String key, String minScore, String maxScore, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reversed ? jedis.zrangeByScoreWithScores(key, minScore, maxScore)
                    : jedis.zrevrangeByScoreWithScores(key, maxScore, minScore);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<Tuple> sortSetRangeByScoreWithScores(String key, double minScore, double maxScore, int offset, int count, boolean reversed) {
        return sortSetRangeByScoreWithScores(key, minScore + "", maxScore + "", offset, count, reversed);
    }

    @Override
    public Set<Tuple> sortSetRangeByScoreWithScores(String key, String minScore, String maxScore, int offset, int count, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return reversed ? jedis.zrangeByScoreWithScores(key, minScore, maxScore, offset, count)
                    : jedis.zrevrangeByScoreWithScores(key, maxScore, minScore, offset, count);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetCount(String key, double minScore, double maxScore) {
        return sortSetCount(key, minScore + "", maxScore + "");
    }

    @Override
    public Long sortSetCount(String key, String minScore, String maxScore) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zcount(key, minScore, maxScore);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetRemoveRangeByRank(String key, long startRank, long endRank) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zremrangeByRank(key, startRank, endRank);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetRemoveRangeByScore(String key, double minScore, double maxScore) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zremrangeByScore(key, minScore, maxScore);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetInterStore(String destination, String... sources) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zinterstore(destination, sources);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetInterStore(String destination, ZParams params, String... sources) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zinterstore(destination, params, sources);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetUnionStore(String destination, String... sources) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zunionstore(destination, sources);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long sortSetUnionStore(String destination, ZParams params, String... sources) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zunionstore(destination, params, sources);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Tuple> sortSetScan(String key, String cursor) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zscan(key, cursor);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public ScanResult<Tuple> sortSetScan(String key, String cursor, ScanParams params) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.zscan(key, cursor, params);
        } finally {
            T.releaseResource(jedis);
        }
    }
}
