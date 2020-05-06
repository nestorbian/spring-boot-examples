package com.nestor.mybatisdemo.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.nestor.mybatisdemo.enums.BaseEnum;
import com.nestor.mybatisdemo.util.EnumUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * BaseEnum反序列化定制
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
public class BaseEnumJsonDeserializer<T extends BaseEnum> extends JsonDeserializer<T> implements
        ContextualDeserializer {

    private Class<T> targetClass;

    public BaseEnumJsonDeserializer() {
    }

    public BaseEnumJsonDeserializer(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {
        T value = (T) EnumUtil.getByCode(jsonParser.getValueAsString(), targetClass);
        if (Objects.isNull(value)) {
            value = (T) EnumUtil.getByCode(jsonParser.getIntValue(), targetClass);
        }
        return value;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        // 获取带注释的类的类类型
        targetClass = (Class<T>) ctxt.getContextualType().getRawClass();
        // 这个新的Deserializer将被缓存
        return new BaseEnumJsonDeserializer(targetClass);
    }

}
