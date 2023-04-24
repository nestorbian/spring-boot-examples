package com.nestor.hibernatevalidation.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/2/11
 */
public class DateFormatFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        return call(env, arg1, null);
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Object javaObject = FunctionUtils.getJavaObject(arg1, env);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        if (arg2 != null) {
            String pattern = FunctionUtils.getStringValue(arg2, env);
            if (StringUtils.hasText(pattern)) {
                formatter = DateTimeFormatter.ofPattern(pattern);
            }
        }

        if (javaObject instanceof LocalDate) {
            LocalDate date = (LocalDate) javaObject;
            return new AviatorString(date.format(formatter));
        }

        throw new IllegalArgumentException(getName() + "函数入参类型" + javaObject.getClass().getName() + "不支持！");
    }

    @Override
    public String getName() {
        return "dateFormat";
    }

    public static void main(String[] args) {
        AviatorEvaluator.addFunction(new DateFormatFunction());

        Map<String, Object> env = new HashMap<>();
        env.put("currentDate", LocalDate.now());

        System.out.println(AviatorEvaluator.execute("dateFormat(currentDate)", env));
        System.out.println(AviatorEvaluator.execute("dateFormat(currentDate, 'yyyyMMdd')", env));

        Map<String, Object> env2 = new HashMap<>();
        env2.put("currentDate", LocalDateTime.now());
        System.out.println(AviatorEvaluator.execute("dateFormat(currentDate, 'yyyyMMdd')", env2));
    }
}
