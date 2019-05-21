package com.mingrn.common.redis.util.serizlizer;


import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 序列化工具
 * 这里使用的序列化工具是 protostuff, java 内置 Serialize
 * 工具速度较慢, 不能作为 Redis 序列化工具使用。
 * 关于 java序列化/反序列化之xstream、protobuf、protostuff 的比较与使用例子
 * 见:
 * <a herf="https://www.cnblogs.com/xiaoMzjm/p/4555209.html" >比较案例</a>
 * </p>
 * 使用示例:
 * <code>
 *     // 序列化
 *     byte[] bytes = SerializerUtil.serialize("Str", String.class);
 *     // 反序列化
 *     String str = SerializerUtil.deserialize(bytes, String.class);
 * </code>
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 */
public class SerializerUtil {

	private SerializerUtil(){}

	/**
	 * 序列化
	 * <code>
	 * serialize("str", String.class);
	 * </code>
	 *
	 * @param source 序列化的泛型 <T> 资源
	 * @param typeClass 泛型 <T> 对应 class 类型
	 * @return 泛型 <T> 序列化后的 Byte 数组
	 */
	public static <T> byte[] serialize(final T source, Class<T> typeClass) {
		Schema<T> schema = RuntimeSchema.createFrom(typeClass);
		final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			return serializeInternal(source, schema, buffer);
		} catch (final Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 * <code>
	 * byte[] bytes = serialize("str", String.class);
	 * String str = deserialize(bytes, String.class);
	 * </code>
	 *
	 * @param bytes 泛型 <T> 序列化后的 Byte 数组
	 * @param typeClass 泛型 <T> 对应 class 类型
	 * @return Byte 数组反序列化后的资源
	 */
	public static <T> T deserialize(final byte[] bytes, Class<T> typeClass) {
		try {
			Schema<T> schema = RuntimeSchema.createFrom(typeClass);
			return deserializeInternal(bytes, schema.newMessage(), schema);
		} catch (final Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	/**
	 * @param source 序列化的泛型 <T> 资源
	 * @param schema {@link Schema}
	 * @param buffer {@link LinkedBuffer}
	 */
	private static <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
		return ProtostuffIOUtil.toByteArray(source, schema, buffer);
	}

	/**
	 * @param bytes 泛型 <T> 序列化后的 Byte 数组
	 * @param t 泛型 <T>
	 * @param schema {@link Schema}
	 */
	private static <T> T deserializeInternal(final byte[] bytes, final T t, final Schema<T> schema) {
		ProtostuffIOUtil.mergeFrom(bytes, t, schema);
		return t;
	}
}
