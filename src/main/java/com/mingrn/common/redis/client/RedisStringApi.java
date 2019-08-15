package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;

/**
 * Redis String API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-11 22:25
 */
public interface RedisStringApi extends BaseRedisApi {

    /**
     * 设置键 - 值
     *
     * @param key    键
     * @param val    值
     * @param binary 是否使用二进制
     * @return true: 设置成功, false: 设置失败
     */
    boolean set(String key, String val, boolean binary);

    /**
     * 设置键 - 值, 键必须不存在
     *
     * @param key    键
     * @param val    值
     * @param binary 是否使用二进制
     * @return true: 设置成功, false: 设置失败
     */
    boolean setAndNotExist(String key, String val, boolean binary);


    /**
     * 设置键 - 值
     *
     * @param key        键
     * @param val        值
     * @param existOrNot 键是否存在
     * @param binary     是否使用二进制
     * @return true: 设置成功, false: 设置失败
     */
    boolean setExistOrNot(String key, String val, boolean existOrNot, boolean binary);

    /**
     * 设置键 - 值
     * 设置 {@code seconds} 秒后过期
     *
     * @param key     键
     * @param val     值
     * @param seconds 秒,过期时间
     * @param binary  是否使用二进制
     * @return true: 设置成功, false: 设置失败
     */
    boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary);

    /**
     * 设置键 - 值
     * 设置 {@code seconds} 秒后过期, 并选定键是否必须存在
     *
     * @param key        键
     * @param val        值
     * @param seconds    秒,过期时间
     * @param binary     是否使用二进制
     * @param existOrNot 键是否存在
     * @return true: 设置成功, false: 设置失败
     */
    boolean setExpireAtSeconds(String key, String val, int seconds, boolean binary, boolean existOrNot);

    /**
     * 设置键 - 值
     * 设置 {@code millis} 毫秒后过期, 并选定键是否必须存在
     *
     * @param key        键
     * @param val        值
     * @param millis     毫秒,过期时间
     * @param binary     是否使用二进制
     * @param existOrNot 键是否存在
     * @return true: 设置成功, false: 设置失败
     */
    boolean setExpireAtMillis(String key, String val, long millis, boolean binary, boolean existOrNot);

    /**
     * 通过键获取值
     *
     * @param key 键
     * @return val
     */
    String get(String key);

    /**
     * 通过键获取值
     * 通过二进制键获取值
     *
     * @param key 键
     * @return 二进制 val
     */
    byte[] getWithBinaryKey(String key);

    /**
     * 通过键获取值并设置新值
     *
     * @param key    键
     * @param newVal 新值
     * @return oldVal
     */
    String getAndSetNewVal(String key, String newVal);

    /**
     * 使用二进制键获取值并设置新值
     *
     * @param key    键
     * @param newVal 新值
     * @return 二进制 oldVal
     */
    byte[] getAndSetNewValWithBinary(String key, String newVal);

    /**
     * 获取指定下边范围的字符
     *
     * @param key         键
     * @param startOffset 开始下标
     * @param endOffset   结束下标
     * @return 指定范围内字符, 如原始字符为 thisIsVal, 获取下标为 1,4. 返回结果为 hi
     */
    String getRange(String key, long startOffset, long endOffset);

    /**
     * 获取指定下边范围的字符
     *
     * @param key         键
     * @param startOffset 开始下标
     * @param endOffset   结束下标
     * @return 返回指定范围内字符的二进制数据
     */
    byte[] getRangeWithBinary(String key, long startOffset, long endOffset);
}