package com.nestor.mybatisgeneratordemo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json序列化反序列化工具类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/2/1
 */
public class JsonUtil {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 序列化
	 *
	 * @param object
	 * @return java.lang.String
	 * @date : 2021/2/1 18:13
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	public static String toJsonString(Object object) throws Exception {
		return OBJECT_MAPPER.writeValueAsString(object);
	}

	/**
	 * 反序列化
	 *
	 * @param string
	 * @param clazz
	 * @return T
	 * @date : 2021/2/1 18:13
	 * @author : Nestor.Bian
	 * @since : 1.0
	 */
	public static <T> T parse(String string, Class<T> clazz) throws Exception {
		return OBJECT_MAPPER.readValue(string, clazz);
	}

}
