package com.fenglai.common.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class JsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtil() {

    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * 转为json字符串
     */
    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转为格式化后的json串
     */
    public static String toJsonPrettify(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转为json字符串, 忽略空值
     */
    public static String toJsonIgnoreNull(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转为Class对象
     */

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转为List
     *
     * @param jsonArrayStr json串
     * @param clazz        集合中的元素类型
     */
    public static <T> List<T> fromJsonToList(String jsonArrayStr, Class<T> clazz) {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Map<String, Object>> fromJsonToListMap(String jsonArrayStr) {

        JavaType javaType = getCollectionType(ArrayList.class);
        try {
            return OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * json转为Map
     *
     * @param jsonMapStr json串
     */
    public static <K, V> Map<K, V> fromJsonToMap(String jsonMapStr) {

        JavaType javaType = getMapType(Map.class, String.class, Object.class);
        try {
            return OBJECT_MAPPER.readValue(jsonMapStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * json转为Map
     *
     * @param jsonMapStr json串
     * @param valueClass value类型
     */
    public static <K, V> Map<K, V> fromJsonToMap(String jsonMapStr, Class<V> valueClass) {

        JavaType javaType = getMapType(Map.class, String.class, valueClass);
        try {
            return OBJECT_MAPPER.readValue(jsonMapStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> fromJsonToMap(String jsonMapStr, Class<K> keyClass, Class<V> valueClass) {

        JavaType javaType = getMapType(Map.class, keyClass, valueClass);
        try {
            return OBJECT_MAPPER.readValue(jsonMapStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * 获取collection中的泛型
     *
     * @param collectionClass 泛型的Collection
     * @param elementClass    集中中的元素类型
     * @return JavaType Java类型
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClass) {
        return OBJECT_MAPPER.getTypeFactory().constructCollectionLikeType(collectionClass, elementClass);
    }

    private static JavaType getCollectionType(Class<? extends Collection> collectionClass) {
        JavaType mapType = getMapType(Map.class, String.class, Object.class);
        return OBJECT_MAPPER.getTypeFactory().constructCollectionType(collectionClass, mapType);
    }

    /**
     * 获取Map中的泛型
     *
     * @param mapClass   泛型的Collection
     * @param keyClass   Map中key类型
     * @param valueClass Map中value类型
     * @return JavaType Java类型
     */
    private static JavaType getMapType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return OBJECT_MAPPER.getTypeFactory().constructMapLikeType(mapClass, keyClass, valueClass);
    }

    /**
     * 转为 JavaBean
     */
    public static <T> T toBean(Object obj, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(obj, clazz);
    }
}
