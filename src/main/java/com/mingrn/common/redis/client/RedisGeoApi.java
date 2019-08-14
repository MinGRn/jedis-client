package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisApi;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基于 GEO API 接口
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 03/10/2018 19:27
 */
public interface RedisGeoApi extends BaseRedisApi {

	/**
	 * 新增地理位置信息
	 *
	 * @param key 键
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param member 成员
	 * @return {@link Long}成功个数
	 */
	Long geoAdd(String key, Double longitude, Double latitude, String member);

	/**
	 * 批量新增地理位置信息
	 *
	 * @param key 键
	 * @param memberCoordinateMap 成员Map
	 * @return {@link Long}成功个数
	 */
	Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap);

	/**
	 * 获取成员位置
	 *
	 * @param key 键
	 * @param member 成员
	 * @return {@link GeoCoordinate} 成员经纬度信息
	 */
	GeoCoordinate geoPos(String key, String member);

	/**
	 * 批量获取成员位置
	 *
	 * @param key 键
	 * @param members 成员
	 * @return {@link GeoCoordinate} 成员经纬度信息
	 */
	List<GeoCoordinate> geoPos(String key, String... members);


	/**
	 * 获取两个成员之间的距离
	 *
	 * @param key 键
	 * @param member1 成员1
	 * @param member2 成员2
	 * @return {@link Double}距离,单位:米
	 */
	Double geoDist(String key, String member1, String member2);

	/**
	 * 获取两个成员之间的距离
	 *
	 * @param key 键
	 * @param member1 成员1
	 * @param member2 成员2
	 * @param unit {@link GeoUnit}距离单位
	 * @return {@link Double}距离
	 */
	Double geoDist(String key, String member1, String member2, GeoUnit unit);

	/**
	 * 以指定经纬度点为中心,获取指定半径距离内的成员
	 *
	 * @param key 键
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param radius 半径距离
	 * @param unit 半径单位
	 * @return {@link GeoRadiusResponse} 半径内的成员列表
	 */
	List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit);

	/**
	 * 以指定经纬度点为中心,获取指定半径距离内的成员,并增加参数选择 {@see GeoRadiusParam}
	 *
	 * @param key 键
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param radius 半径距离
	 * @param unit 半径单位 {@link GeoUnit}
	 * @param withParam 半径参数
	 * 		e.g. 返回半径: {@code GeoRadiusParam.geoRadiusParam().withDist()}
	 * 		e.g. 返回半径并且正序排序: {@code GeoRadiusParam.geoRadiusParam().withDist().sortDescending()}
	 * @return {@link GeoRadiusResponse} 半径内的成员列表
	 */
	List<GeoRadiusResponse> geoRadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam withParam);

	/**
	 * 以指定成员为中心,获取指定半径距离内的成员
	 *
	 * @param key 键
	 * @param member 指定成员
	 * @param radius 半径距离
	 * @param unit 半径单位 {@link GeoUnit}
	 * @return {@link GeoRadiusResponse} 半径内的成员列表
	 */
	List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit);

	/**
	 * 以指定成员为中心,获取指定半径距离内的成员
	 *
	 * @param key 键
	 * @param member 指定成员
	 * @param radius 半径距离
	 * @param unit 半径单位 {@link GeoUnit}
	 * @param withParam 半径参数
	 * 		e.g. 返回半径: {@code GeoRadiusParam.geoRadiusParam().withDist()}
	 * 		e.g. 返回半径并且正序排序: {@code GeoRadiusParam.geoRadiusParam().withDist().sortDescending()}
	 * @return {@link GeoRadiusResponse} 半径内的成员列表
	 */
	List<GeoRadiusResponse> geoRadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam withParam);

	/**
	 * 获取指定成员的经纬度 Hash 值
	 * <p>
	 * Hash 值越长表示的经纬度越精确,即两个成员之间的精确距离可以通过 Hash 的长度进行得知.
	 * 一般经纬度应该精确到小数点后8位
	 *
	 * @param key     键
	 * @param members 成员
     * @return 返回 hash 值
	 */
	List<String> geoHash(String key, String... members);

	/**
	 * 获取指定key的成员
	 *
	 * @param key   键
	 * @param start 开始范围
	 * @param end   结束范围,为 -1 表示查询 start 之后的所有成员
     * @return 返回成员集合
	 */
	Set<String> geoMembers(String key, int start, int end);
}