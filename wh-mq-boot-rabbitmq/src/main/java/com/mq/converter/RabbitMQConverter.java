package com.mq.converter;

import com.mq.idempotent.core.aop.MessageConverter;
import com.mq.idempotent.core.utils.ReflectUtil;

import java.lang.reflect.Method;

/**
 * @author : wh
 * @date : 2022/1/12 11:11
 * @description:
 */
public class RabbitMQConverter implements MessageConverter<Object> {


    @Override
    public String getUniqueKey(Object o, String field, Method method, Object[] args) {
        try {
            return ReflectUtil.getFieldValue(o, field).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
