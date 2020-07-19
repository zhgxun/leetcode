package com.github.zhgxun.leetcode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonUtils {

    private static final ObjectMapper jacksonMapper;

    static {
        jacksonMapper = new ObjectMapper();
        // 反序列化遇见未知属性时不抛出异常, 默认抛出异常
        jacksonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 遇见不存在的bean时不提示错误
        jacksonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 反序列化遇见空字符串时按NULL对象处理
        jacksonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 序列化属性值为Null排除
        jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // ... 其它需要配置的均可在此配置
        // 更多配置参考 https://github.com/FasterXML/jackson-databind/wiki/Deserialization-Features
    }

    /**
     * 普通对象序列化为字符串
     *
     * @param o 普通对象
     * @return 序列化后的字符串
     * @throws Exception 序列化异常
     */
    public static String toJson(Object o) throws Exception {
        return jacksonMapper.writeValueAsString(o);
    }

    /**
     * 普通对象反序列化, 内部可嵌套
     *
     * @param json  原始json字符串
     * @param clazz 期待解析的目标bean对象
     * @param <T>   目标对象
     * @return 解析后的泛型目标对象
     * @throws Exception 解析异常, 调用捕获
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return jacksonMapper.readValue(json, clazz);
    }

    /**
     * 引用对象解析反序列化, 比如解析成 {@link List} {@link java.util.Map}
     * <p>
     * 该引用比较难构造, 可直接拷贝
     *
     * @param json 原始json字符串, 典型如json数组
     * @param ref  需要借助目标引用类型
     * @param <T>  目标对象
     * @return 解析后的目标泛型对象
     * @throws Exception 解析异常
     */
    public static <T> T fromJson(String json, TypeReference<T> ref) throws Exception {
        return jacksonMapper.readValue(json, ref);
    }
}
