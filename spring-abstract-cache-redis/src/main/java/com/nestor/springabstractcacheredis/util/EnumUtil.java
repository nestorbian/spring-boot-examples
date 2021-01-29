package com.nestor.springabstractcacheredis.util;

import com.nestor.springabstractcacheredis.enums.BaseEnum;

/**
 * 枚举工具类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
public class EnumUtil {

    public static <T extends BaseEnum<E>, E> T getByCode(E code, Class<T> tClass) {
        T[] enumConstants = tClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new RuntimeException(String.format("%s此类非枚举类", tClass.getCanonicalName()));
        }
        for (T enumConstant : enumConstants) {
            if (enumConstant.getValue().equals(code)) {
                return enumConstant;
            }
        }
        return null;
    }

}
