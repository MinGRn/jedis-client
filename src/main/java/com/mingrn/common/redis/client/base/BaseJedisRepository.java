package com.mingrn.common.redis.client.base;

/**
 * Redis 基础共用接口
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 03/10/2018 19:27
 */
public interface BaseJedisRepository {

	/**
	 * 删除键,支持批量
	 *
	 * @param keys 键
	 * @return {@link Long} 成功个数
	 */
	Long delete(String... keys);

	/**
	 * 重命名键,newKey可以已存在
	 *
	 * @param key 键
	 * @param newKey 新键
	 * @return Status code repy
	 */
	String rename(String key, String newKey);

	/**
	 * 重命名键,newKey必须不存在
	 *
	 * @param key 键
	 * @param newKey 新键
	 * @return 1: if the key was renamed; 0 if the target key already exist
	 */
	Long renameNx(String key, String newKey);

	/**
	 * 键设置过期时间,秒级
	 *
	 * @param key 键
	 * @param seconds 超时(过期)时间,单位秒.若设置负值键会立即删除
	 * @return 1:设置成功 0:键不存在
	 */
	Long expire(String key, int seconds);

	/**
	 * 键设置过期时间戳,秒级
	 * e.g.
	 * 2020-01-01 00:00:00 年的秒级时间戳为 1577808000,
	 * 设置 timestamp = 1577808000 则该键到 2020-01-01 00:00:00 后过期
	 *
	 * @param key 键
	 * @param timestamp 过期时间戳
	 * @return 1:设置成功 0:键不存在
	 */
	Long expireAt(String key, long timestamp);

	/**
	 * 键设置过期时间,毫秒级
	 *
	 * @param key 键
	 * @param milliseconds 超时(过期)时间,单位毫秒.若设置负值键会立即删除
	 * @return 1:设置成功 0:键不存在
	 */
	Long pExpire(String key, long milliseconds);

	/**
	 * 键设置过期时间戳,毫秒级
	 *
	 * @param key 键
	 * @param millisecondsTimestamp 键过期时间戳(毫秒级)
	 * @return 1:设置成功 0:键不存在
	 */
	Long pExpireAt(String key, long millisecondsTimestamp);

	/**
	 * 清除过期时间
	 *
	 * @param key 键
	 * @return 1:已清除过期时间 0:键已过期或不存在
	 */
	Long persist(String key);

	/**
	 * 查看剩余超时(过期)时间,秒级
	 *
	 * @param key 键
	 * @return -1: 键未设置过期时间 -2:键不存在 >0:剩余过期时间(秒级)
	 */
	Long ttl(String key);

	/**
	 * 查看剩余超时(过期)时间,毫秒级
	 *
	 * @param key 键
	 * @return -1: 键未设置过期时间 -2:键不存在 >0:剩余过期时间(秒级)
	 */
	Long pTtl(String key);

	/**
	 * 查看键是否存在
	 *
	 * @param keys 键
	 * @return 返回存在个数
	 */
	Long exists(String... keys);

	/**
	 * 键的数据类型
	 *
	 * @param key 键
	 * @return none: 键不存在
	 * 		string,list,set,zset,hash
	 */
	String type(String key);

	/**
	 * 键的数据编码格式
	 *
	 * @param key 键
	 * @return none: 键不存在
	 * 		String: raw,int,embstr
	 * 		hash: hashtable,ziplist
	 * 		list: ziplist,linkedlist,quicklist
	 * 		set: hashtable,intset
	 * 		zet: skiplist,ziplist
	 */
	String keyEncoding(String key);
}
