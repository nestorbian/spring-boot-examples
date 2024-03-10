package com.nestor.hibernatevalidation.support.desensitization;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.ArrayUtil;
import lombok.Data;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/21
 */
@Data
public class DesensitizationPatternLayout extends PatternLayout {

    private Boolean desensitization;

    private RegexReplacement[] replaces;

    @Override
    public String doLayout(ILoggingEvent event) {
        String layout = super.doLayout(event);

        if (Boolean.TRUE.equals(desensitization)) {
            if (ArrayUtil.isNotEmpty(replaces)) {
                for (RegexReplacement replace : replaces) {
                    layout = replace.format(layout);
                }
            }

        }

        return layout;
    }
}
