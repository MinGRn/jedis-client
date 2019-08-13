package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseJedisRepository;

import java.util.Set;

/**
 * Redis 基于 SET API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-13 20:50
 */
public interface JedisSetRepository extends BaseJedisRepository {

    /**
     * 集合添加成员
     *
     * @param key     键
     * @param members 成员
     * @return specifically: 1 if the new element was added 0 if the element was
     * already a member of the set
     */
    Long setAdd(String key, String... members);

    /**
     * 集合删除成员
     *
     * @param key     键
     * @param members 成员
     * @return specifically: 1 if the new element was removed 0 if the new element was
     * not a member of the set
     */
    Long setRemove(String key, String... members);

    /**
     * 获取某个元素是否在集合中
     *
     * @param key    键
     * @param member 成员
     * @return true: if the element is a member of the set. false: if the element
     * is not a member of the set OR if the key does not exist
     */
    Boolean setIsMember(String key, String member);

    /**
     * 从集合中随机弹出并删除一个元素
     *
     * @param key 键
     * @return 元素
     */
    String setPop(String key);

    /**
     * 从集合中随机弹出并删除 count 个元素
     *
     * @param key 键
     * @return 元素集合
     */
    Set<String> setPop(String key, long count);

    /**
     * 获取集合中的元素
     *
     * @param key 键
     * @return 元素集合
     */
    Set<String> setMembers(String key);

    /**
     * 将 source 集合中的 member 元素迁移至 destination 集合
     *
     * @param source      源集合
     * @param destination 目标集合
     * @param member      迁移成员
     * @return specifically: 1 if the element was moved 0 if the element was not found
     * on the first set and no operation was performed
     */
    Long setMove(String source, String destination, String member);

    /**
     * 求多个集合的交集
     *
     * @param keys 集合键
     * @return 交集集合
     */
    Set<String> setInter(String... keys);

    /**
     * 求多个集合的并集
     *
     * @param keys 集合键
     * @return 并集集合
     */
    Set<String> setUnion(String... keys);

    /**
     * 求多个集合的差集
     *
     * @param keys 集合键
     * @return 差集集合
     */
    Set<String> setDiff(String... keys);

    /**
     * 求多个集合的交集,并将结果存储到新的 destination 集合
     *
     * @param destination 存储集合
     * @param keys        集合键
     * @return
     */
    Long setInterAndStore(String destination, String... keys);

    /**
     * 求多个集合的并集, 并将结果存储到新的 destination 集合
     *
     * @param destination 存储集合
     * @param keys        集合键
     * @return
     */
    Long setUnionAndStore(String destination, String... keys);

    /**
     * 求多个集合的差集, 并将结果存储到新的 destination 集合
     *
     * @param destination 存储集合
     * @param keys        集合键
     * @return
     */
    Long setDiffAndStore(String destination, String... keys);
}