package com.nestor.hibernatevalidation;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/12/29
 */
public class Hello {

    public static void main(String[] args) throws Exception {
        // URL resource = Hello.class.getResource("Hello1.class");
        // System.out.println(resource);
        // PathMatchingResourcePatternResolver patternResolver =
        //         new PathMatchingResourcePatternResolver();
        // Resource[] resources = patternResolver.getResources("classpath:**/*MyHello.class");
        // System.out.println(Arrays.toString(resources));
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("orderItem.itemName");
        System.out.println(expression.getValue(new Order(new OrderItem("item")),
                String.class));


    }

    // @Data
    @AllArgsConstructor
    public static class Order {
        @Getter
        private OrderItem orderItem;
    }

    // @Data
    @AllArgsConstructor
    public static class OrderItem {
        @Getter
        private String itemName;
    }
}
