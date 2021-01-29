package com.nestor.mybatisdemo.util;

import org.springframework.util.StringUtils;

/**
 * 转义工具类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/15
 */
public class EscapeUtil {

    public static String escapeChar(String before){
        if(!StringUtils.isEmpty(before)){
            before = before.replace("\\", "\\\\");
            before = before.replace("_", "\\_");
            before = before.replace("%", "\\%");
        }
        return before ;
    }

}
