package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.util.Set;

/**
 * Redis Sort Set API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/8/14 10:20
 */
public interface RedisSortSetApi extends BaseRedisApi {

    /**
     * 新增或修改成员(分数)
     *
     * @param key    键
     * @param member 成员
     * @param score  分数
     * @return 成功添加个数
     */
    Long sortSetAdd(String key, String member, double score);

    /**
     * 新增或修改成员(分数)
     *<p>
     * ZAddParams 是可选参数.
     * <pre>{@code
     *
     *   // 新增: Linda 成员必须不存在
     *   zAdd("u", "Linda", 100.00, ZAddParams.zAddParams().nx()).
     *
     * }</pre>
     *
     * @param key    键
     * @param member 成员
     * @param score  分数
     * @param params 可选参数:
     *               <ul>
     *                  <li>nx: member 必须不存在</li>
     *                  <li>xx: member 必须存在</li>
     *                  <li>ch: 返回此次操作后,有序集合元素和分数变化的个数</li>
     *                  <li>incr: 对 score 做增加, 同 {@link }</li>
     *               </ul>
     * @return 新增或修改成员(分数)
     * @see redis.clients.jedis.params.sortedset.ZAddParams
     */
    Long sortSetAdd(String key, String member, double score, ZAddParams params);

    /**
     * 计算集合成员个数
     *
     * @param key 键
     * @return 成员个数
     */
    Long sortSetCard(String key);

    /**
     * 计算某个成员分数
     *
     * @param key    键
     * @param member 成员
     * @return 成员分数
     */
    Double sortSetScore(String key, String member);

    /**
     * 计算成员排名
     *
     * @param key     键
     * @param member  成员
     * @param reverse true: 按成员分数由高到低排名,即分数越大排名越高.
     *                false: 按成员分数由低到高排名,即分数越小排名越高.
     * @return 成员排名名次
     */
    Long sortSetRank(String key, String member, boolean reverse);

    /**
     * 删除成员
     *
     * @param key     键
     * @param members 成员
     * @return 成功删除个数
     */
    Long sortSetRemove(String key, String... members);

    /**
     * 给成员增加指定分数
     *
     * @param key    键
     * @param member 成员
     * @param score  分数
     * @return 新增后的分数
     */
    Double sortSetScoreIncrBy(String key, String member, double score);

    /**
     * 获取指定排名范围内的成员(按分数).
     *
     * @param key      键
     * @param minRank  最低排名
     * @param maxRank  最高排名
     * @param reversed true: 按成员分数由高到低排名, false 按成员分数由底到高排名
     * @return 成员集合
     */
    Set<String> sortSetRange(String key, long minRank, long maxRank, boolean reversed);

    /**
     * 获取指定分数范围内的成员.
     * 注意, 该接口与 {@link #sortSetRange(String, long, long, boolean)} 不同.
     *
     * @param key 键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名, false: 按分数由低到高排名
     * @return 成员集合
     */
    Set<String> sortSetRangeByScore(String key, double minScore, double maxScore, boolean reversed);

    /**
     * 获取指定分数范围内的成员. 同 {@link #sortSetRangeByScore(String, double, double, boolean)}.
     * 区别是该接口支持开(小括号)闭(中括号)区间,另外还有如下表示方式:
     * -inf: 无限小
     * +inf: 无限大
     * <p>
     * 示例: 查找最小分数等于 200, 最大分数无上限的用户:
     * <pre>{@code
     *   sortSetRangeByScore("u:rank", "[200", "+inf");
     * }</pre>
     *
     * @param key      键
     * @param minScore 最小分数, 支持标识符如: -inf
     * @param maxScore 最大分数, 支持标识符如: +inf
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @return 成员集合
     */
    Set<String> sortSetRangeByScore(String key, String minScore, String maxScore, boolean reversed);

    /**
     * 同 {@link #sortSetRangeByScore(String, double, double, boolean)}.
     * 在此基础上增加 起始位置 与 返回个数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @param offset   起始位置
     * @param count    个数
     * @return 成员集合
     */
    Set<String> sortSetRangeByScore(String key, double minScore, double maxScore, boolean reversed, int offset, int count);

    /**
     * 同 {@link #sortSetRangeByScore(String, String, String, boolean)}.
     * 在此基础上增加 起始位置 与 返回个数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @param offset   起始位置
     * @param count    个数
     * @return 成员集合
     */
    Set<String> sortSetRangeByScore(String key, String minScore, String maxScore, boolean reversed, int offset, int count);

    /**
     * 获取指定分数范围内的成员, 并返回成员分数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @return 成员集合
     */
    Set<Tuple> sortSetRangeByScoreWithScores(String key, double minScore, double maxScore, boolean reversed);

    /**
     * 获取指定分数范围内的成员, 并返回成员分数.
     * 该接口支持开(小括号)闭(中括号)区间,另外还有如下表示方式:
     * -inf: 无限小
     * +inf: 无限大
     * <p>
     * 可参见: {@link #sortSetRangeByScore(String, String, String, boolean)}
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @return 成员集合
     */
    Set<Tuple> sortSetRangeByScoreWithScores(String key, String minScore, String maxScore, boolean reversed);

    /**
     * 同 {@link #sortSetRangeByScoreWithScores(String, double, double, boolean)}.
     * 在此基础上增加 起始位置 与 返回个数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名.
     *                 false: 按分数由低到高排名.
     * @param offset   起始位置
     * @param count    个数
     * @return 成员集合
     */
    Set<Tuple> sortSetRangeByScoreWithScores(String key, double minScore, double maxScore, int offset, int count, boolean reversed);

    /**
     * 同 {@link #sortSetRangeByScoreWithScores(String, String, String, boolean)}.
     * 在此基础上增加 起始位置 与 返回个数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @param reversed true: 按分数由高到低排名。
     *                 false: 按分数由低到高排名。
     * @param offset   起始位置
     * @param count    个数
     * @return 成员集合
     */
    Set<Tuple> sortSetRangeByScoreWithScores(String key, String minScore, String maxScore, int offset, int count, boolean reversed);

    /**
     * 返回指分数范围内的成员个数
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return 成员个数
     */
    Long sortSetCount(String key, double minScore, double maxScore);

    /**
     * 返回指分数范围内的成员个数
     * 该接口支持开(小括号)闭(中括号)区间,另外还有如下表示方式:
     * -inf: 无限小
     * +inf: 无限大
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return 成员个数
     */
    Long sortSetCount(String key, String minScore, String maxScore);

    /**
     * 按升序删除指定排名内的成员
     *
     * @param key       键
     * @param startRank 开始排名
     * @param endRank   结束排名
     * @return 成功删除个数
     */
    Long sortSetRemoveRangeByRank(String key, long startRank, long endRank);

    /**
     * 删除指定分数范围内的成员
     *
     * @param key      键
     * @param minScore 最小分数
     * @param maxScore 最大分数
     * @return 成功删除个数
     */
    Long sortSetRemoveRangeByScore(String key, double minScore, double maxScore);

    /**
     * 集合求交集, 并将结果存储到新的 destination 集合
     *
     * @param destination 目标存储集合
     * @param sources     源集合
     */
    Long sortSetInterStore(String destination, String... sources);

    /**
     * 集合求交集, 并将结果存储到新的 destination 集合.
     * 可选参数见 {@link redis.clients.jedis.ZParams params}.
     * 具体可选参数如下:
     *  weights: 每个键的权重, 在做交集计算时, 每个键中的每个 member 会将自己的分数乘以这个权重值, 默认为 1.
     *  Aggregate: 计算成员交集后分值可以按照 SUM, MIN 以及 MAX 做汇总, 默认是 SUM.
     *
     * @param destination 目标存储集合
     * @param params      可选参数
     * @param sources     源集合
     * @return
     */
    Long sortSetInterStore(String destination, ZParams params, String... sources);

    /**
     * 集合求并集, 并将结果存储到新的 destination 集合
     *
     * @param destination 目标存储集合
     * @param sources     源集合
     * @return
     */
    Long sortSetUnionStore(String destination, String... sources);

    /**
     * 集合求并集, 并将结果存储到新的 destination 集合
     * 可选参数同 {@link #sortSetInterStore(String, ZParams, String...)}
     *
     * @param destination 目标存储集合
     * @param params      可选参数
     * @param sources     源集合
     * @return
     */
    Long sortSetUnionStore(String destination, ZParams params, String... sources);

    /**
     * 迭代集合,sortSetScan 命令同 {@link BaseRedisApi#scan(String)},
     * 用于迭代获取成员, 初始 {@code cursor} 值应为 "0", 下次的 cursor 值
     * 应为上次迭代返回的 cursor 值
     *
     * @param key    键
     * @param cursor 游标
     * @return 成员集合
     */
    ScanResult<Tuple> sortSetScan(final String key, final String cursor);

    /**
     * 迭代集合,sortSetScan 命令同 {@link BaseRedisApi#scan(String)},
     * 用于迭代获取成员, 初始 {@code cursor} 值应为 "0", 下次的 cursor 值
     * 应为上次迭代返回的 cursor 值
     *
     * @param key    键
     * @param cursor 游标
     * @param params 匹配模式
     * @return 成员集合
     */
    ScanResult<Tuple> sortSetScan(final String key, final String cursor, final ScanParams params);
}
