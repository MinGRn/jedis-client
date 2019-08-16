package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.*;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.util.Set;

/**
 * Redis Sort Set API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/8/14 10:20
 */
public class RedisSortSetClient<T extends RedisPoolConfig> extends BaseRedisClient<T> implements RedisSortSetApi {

    public RedisSortSetClient() {
        super();
    }

    @Override
    public void setPoolConfig(T poolConfig) {
        this.poolConfig = poolConfig;
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