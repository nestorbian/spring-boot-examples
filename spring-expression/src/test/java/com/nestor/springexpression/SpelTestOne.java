package com.nestor.springexpression;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/19
 */
@SpringBootTest
@Slf4j
public class SpelTestOne {

    private ExpressionParser parser =  new SpelExpressionParser();

    @Value("#{new com.nestor.springexpression.SpelTestOne.User().toString()}")
    private String user;

    @Test
    public void test() {
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        log.info(message);

        exp = parser.parseExpression("6");
        Integer value = exp.getValue(Integer.class);
        log.info("" + value);
    }

    @Test
    public void test1() {
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = exp.getValue(String.class);
        log.info(message);

        exp = parser.parseExpression("'Hello World'.length()");
        Integer size = exp.getValue(Integer.class);
        log.info("" + size);

        exp = parser.parseExpression("'Hello World'.split(' ')[0]");
        message = exp.getValue(String.class);
        log.info(message);
    }


    @Test
    public void test2() {
        User user = new User("John", "Doe",  true, "john.doe@acme.com",30);
        Expression exp = parser.parseExpression("firstName");
        log.info((String)exp.getValue(user));

        exp = parser.parseExpression("email.split('@')[0]");
        String emailId = exp.getValue(user, String.class);
        log.info(emailId);

        exp = parser.parseExpression("age");
        Integer age = exp.getValue(user, Integer.class);
        log.info("" + age);
    }

    @Test
    public void test3() {
        User user = new User("John", "Doe", true,"john.doe@acme.com",  30);
        Expression exp = parser.parseExpression("age > 18");
        log.info("" + exp.getValue(user,Boolean.class));
    }

    @Test
    public void test4() {
        User user = new User("John", "Doe",  true, "john.doe@acme.com",30);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", user);

        Expression exp = parser.parseExpression("#user.getAdmin()");
        Boolean result = exp.getValue(context,Boolean.class);
        log.info("" + result);
    }

    @Test
    public void test5() {
        try {
            StandardEvaluationContext context = new StandardEvaluationContext();
            context.registerFunction("isURLValid",
                    StringHelper.class.getDeclaredMethod("isValid", new Class[] { String.class }));

            String expression = "#isURLValid('http://google.com')";

            Boolean isValid = parser.parseExpression(expression).getValue(context, Boolean.class);
            log.info("" + isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {
        log.info(user);
    }

    public static class StringHelper {
        public static boolean isValid(String url){
            return true;
        }
    }

    public static class User {
        private String firstName;
        private String name1;
        private Boolean admin;
        private String email;
        private Integer age;

        public User(String firstName, String name1, Boolean admin, String email, Integer age) {
            this.firstName = firstName;
            this.name1 = name1;
            this.admin = admin;
            this.email = email;
            this.age = age;
        }

        public User() {
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public Boolean getAdmin() {
            return admin;
        }

        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "firstName='" + firstName + '\'' +
                    ", name1='" + name1 + '\'' +
                    ", admin=" + admin +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
