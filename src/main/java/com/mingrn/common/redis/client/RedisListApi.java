package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;

import java.util.List;

/**
 * Redis List API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-12 21:28
 */
public interface RedisListApi extends BaseRedisApi {

    /**
     * 向列表插入元素
     *
     * @param key       键
     * @param fromRight true: 从左边插入元素, false: 从右边
     * @param members   成员
     * @return 插入成功个数
     */
    Long listPush(String key, boolean fromRight, String... members);

    /**
     * 向列表指定元素前(后)插入元素
     *
     * @param key    键
     * @param before true: 在指定元素前插入元素, false: 指定元素后插入元素
     * @param pivot  参考元素
     * @param member 添加成员
     * @return 当前成员数
     */
    Long listInsert(String key, boolean before, String pivot, String member);

    /**
     * 从列表中删除一个元素
     *
     * @param key       键
     * @param fromRight true: 从列表右边弹出, false: 从列表左边弹出元素
     * @return
     */
    String listPop(String key, boolean fromRight);

    /**
     * 从列表中删除一个元素(阻塞)
     *
     * @param keys      键
     * @param fromRight true: 从列表右边弹出, false: 从列表左边弹出元素
     * @param timeout   超时时间
     * @return
     */
    List<String> listBlockPop(boolean fromRight, int timeout, String... keys);

    /**
     * 删除列表中等于 member 的成员(元素)
     * <p>
     * 当 count > 0 时, 从左到右删除最多 count 个元素.
     * 当 count < 0 时, 从右到左删除最多 |count| 个元素.
     * 当 count = 0 时, 删除多有
     *
     * @param key    键
     * @param count  删除元素个数
     * @param member 指定元素
     * @return 删除成功个数
     */
    Long listRemove(String key, int count, String member);

    /**
     * 修剪(保留)指定范围(下标)内的元素,从 0 开始.
     * <p>
     * 如当前列表中元素为: a, b, c, d, e.
     * 保留下标范围为 1,3.
     * 最终列表剩余元素为: b, c, d
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return
     */
    String listTrim(String key, long start, long end);

    /**
     * 查找指定范围内的元素
     * <p>
     * 如当前列表中元素为: a, b, c, d, e.
     * 查找下标范围为 1,3.
     * 获取的元素为: b, c, d
     * <p>
     * 当 end 为 -1 时表示查找 start 之后的所有元素
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return 获取的元素集合
     */
    List<String> listRange(String key, long start, long end);

    /**
     * 获取指定下标下的元素
     *
     * @param key   键
     * @param index 下标
     * @return 获取的元素
     */
    String listGetByIndex(String key, long index);

    /**
     * 获取列表长度
     *
     * @param key 键
     * @return The length of the list.
     */
    Long listLen(String key);

    /**
     * 向指定下标位置设置元素(替换旧元素), 如果下标超过列表范围则抛出错误
     *
     * @param key    键
     * @param index  下标
     * @param member 元素
     * @return
     */
    String listSet(String key, long index, String member);
}