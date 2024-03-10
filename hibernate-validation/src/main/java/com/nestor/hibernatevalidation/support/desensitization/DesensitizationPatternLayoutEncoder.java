package com.nestor.hibernatevalidation.support.desensitization;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 脱敏Encoder
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2024/1/21
 */
public class DesensitizationPatternLayoutEncoder extends PatternLayoutEncoderBase<ILoggingEvent> {

    private Boolean desensitization;

    private final List<RegexReplacement> replaces = new ArrayList<>();

    @Override
    public void start() {
        DesensitizationPatternLayout patternLayout = new DesensitizationPatternLayout();
        patternLayout.setContext(context);
        patternLayout.setPattern(getPattern());
        patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
        patternLayout.setDesensitization(desensitization);
        patternLayout.setReplaces(replaces.toArray(new RegexReplacement[0]));
        patternLayout.start();
        this.layout = patternLayout;
        super.start();
    }

    public void setDesensitization(Boolean desensitization) {
        this.desensitization = desensitization;
    }

    public void setReplace(RegexReplacement regexReplacement) {
        this.replaces.add(regexReplacement);
    }

}
