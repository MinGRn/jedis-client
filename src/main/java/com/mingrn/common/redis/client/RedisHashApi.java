package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis hash API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019/8/12 11:07
 */
public interface RedisHashApi extends BaseRedisApi {

    /**
     * @param key    键
     * @param field  字段
     * @param val    值
     * @param binary 使用二进制存储
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is
     * returned, otherwise if a new field is created 1 is returned.
     */
    long hSet(String key, String field, String val, boolean binary);

    /**
     * @param key  键
     * @param hash 字段 - 值
     * @return Return true or Exception if hash is empty
     */
    boolean hManySet(String key, Map<String, String> hash);

    /**
     * Set the specified hash field to the specified value if the field not exists.
     *
     * @param key   键
     * @param field 字段
     * @param val   值
     * @return If the field already exists, false is returned, otherwise if a new field is created true is
     * returned.
     */
    boolean hSetAndNotExist(String key, String field, String val);

    /**
     * 指定字段做整型自增操作
     *
     * @param key   键
     * @param field 字段
     * @param val   值
     * @return 自增操作后的值
     */
    long hIncrBy(String key, String field, long val);

    /**
     * 指定字段做浮点自增操作
     *
     * @param key   键
     * @param field 字段
     * @param val   值
     * @return 自增操作后的值
     */
    double hIncrByFloat(String key, String field, double val);

    /**
     * 获取指定字段值
     *
     * @param key   键
     * @param field 字段
     * @return 字段值
     */
    String hGet(String key, String field);

    /**
     * 获取指定key所有字段 - 值
     *
     * @param key 键
     */
    Map<String, String> hGetAll(String key);

    /**
     * 指定字段是否存在
     *
     * @param key   键
     * @param field 字段
     */
    Boolean hFieldExist(String key, String field);

    /**
     * 批量获取字段值
     *
     * @param key    键
     * @param fields 字段
     */
    List<String> hManyGet(String key, String... fields);

    /**
     * 获取指定key下字段数量
     *
     * @param key 键
     */
    Long hLen(String key);

    /**
     * 获取指定 key 下所有字段
     *
     * @param key 键
     * @return fields
     */
    Set<String> hKeys(String key);

    /**
     * 获取指定 key 下所有 val
     *
     * @param key 键
     * @return vals
     */
    List<String> hVals(String key);

    /**
     * 迭代哈希键值对
     * <p>
     * hscan 命令同 scan, 用于迭代获取键值, 初始值应为 "0", 下次的 cursor 值
     * 应为上次迭代返回的 cursor 值
     *
     * @param key    键
     * @param cursor 游标,初始值应为 "0"
     * @return 迭代键值
     */
    ScanResult<Map.Entry<String, String>> hScan(final String key, final String cursor);

    /**
     * 迭代哈希键值对
     * <p>
     * hscan 命令同 {@link BaseRedisApi#scan(String)},
     * 用于迭代获取键值, 初始值应为 "0", 下次的 cursor 值
     * 应为上次迭代返回的 cursor 值
     *
     * @param key    键
     * @param cursor 游标,初始值应为 "0"
     * @param params 匹配模式, 如字段为 name, age. 使用 name* 模式将只返回 name 字段值
     * @return 迭代键值
     */
    ScanResult<Map.Entry<String, String>> hScan(final String key, final String cursor, final ScanParams params);

    /**
     * 批量删除字段
     *
     * @param key   键
     * @param field 字段
     * @return 删除个数
     */
    Long hDel(String key, String... field);
}