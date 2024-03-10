package com.nestor.hibernatevalidation.support.desensitization;

import lombok.Data;

/**
 * 正则替换
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/21
 */
@Data
public class RegexReplacement {

    private String regex;

    private String replacement;

    public String format(String msg) {
        return msg.replaceAll(regex, replacement);
    }

}
