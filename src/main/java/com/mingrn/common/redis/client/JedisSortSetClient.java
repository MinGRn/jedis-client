package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.util.Set;

public class JedisSortSetClient extends BaseJedisClient implements JedisSortSetRepository {
    @Override
    public Long zAdd(String key, String member, double score) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zadd(key, score, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zAdd(String key, String member, double score, ZAddParams params) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zadd(key, score, member, params);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zCard(String key) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zcard(key);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Double zScore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zscore(key, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zRank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zrank(key, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zRevRank(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zrevrank(key, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zRemove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zrem(key, members);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Double zScoreIncrBy(String key, String member, double score) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zincrby(key, score, member);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> zRange(String key, long minRank, long maxRank, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return reversed ? jedis.zrevrange(key, minRank, maxRank) : jedis.zrange(key, minRank, maxRank);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> zRangeByScore(String key, double minScore, double maxScore, boolean reversed) {
        return zRangeByScore(key, minScore + "", maxScore + "", reversed);
    }

    @Override
    public Set<String> zRangeByScore(String key, String minScore, String maxScore, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return reversed ? jedis.zrevrangeByScore(key, maxScore, minScore) : jedis.zrangeByScore(key, minScore, maxScore);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> zRangeByScore(String key, double minScore, double maxScore, boolean reversed, int offset, int count) {
        return zRangeByScore(key, minScore + "", maxScore + "", reversed, offset, count);
    }

    @Override
    public Set<String> zRangeByScore(String key, String minScore, String maxScore, boolean reversed, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return reversed ? jedis.zrevrangeByScore(key, maxScore, minScore, offset, count)
                    : jedis.zrangeByScore(key, minScore, maxScore, offset, count);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(String key, double minScore, double maxScore, boolean reversed) {
        return zRangeByScoreWithScores(key, minScore + "", maxScore + "", reversed);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(String key, String minScore, String maxScore, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return reversed ? jedis.zrangeByScoreWithScores(key, minScore, maxScore)
                    : jedis.zrevrangeByScoreWithScores(key, maxScore, minScore);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(String key, double minScore, double maxScore, int offset, int count, boolean reversed) {
        return zRangeByScoreWithScores(key, minScore + "", maxScore + "", offset, count, reversed);
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(String key, String minScore, String maxScore, int offset, int count, boolean reversed) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return reversed ? jedis.zrangeByScoreWithScores(key, minScore, maxScore, offset, count)
                    : jedis.zrevrangeByScoreWithScores(key, maxScore, minScore, offset, count);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zCount(String key, double minScore, double maxScore) {
        return zCount(key, minScore + "", maxScore + "");
    }

    @Override
    public Long zCount(String key, String minScore, String maxScore) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zcount(key, minScore, maxScore);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zRemoveRangeByRank(String key, long startRank, long endRank) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zremrangeByRank(key, startRank, endRank);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zRemoveRangeByScore(String key, double minScore, double maxScore) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zremrangeByScore(key, minScore, maxScore);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zInterStore(String destination, String... sources) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zinterstore(destination, sources);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zInterStore(String destination, ZParams params, String... sources) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zinterstore(destination, params, sources);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zUnionStore(String destination, String... sources) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zunionstore(destination, sources);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }

    @Override
    public Long zUnionStore(String destination, ZParams params, String... sources) {
        Jedis jedis = null;
        try {
            jedis = RedisPoolConfig.acquireResource();
            return jedis.zunionstore(destination, params, sources);
        } finally {
            RedisPoolConfig.releaseResource(jedis);
        }
    }
}