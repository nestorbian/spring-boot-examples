package com.nestor.springexpression;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/19
 */
@SpringBootTest
@Slf4j
public class SpelTestOne {

    private ExpressionParser parser = new SpelExpressionParser();

    @Value("#{new com.nestor.springexpression.SpelTestOne.User().toString()}")
    private String user;

    @Test
    public void testParserContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'Hello '}#{'World!'}";
        Expression expression = parser.parseExpression(template, parserContext);
        System.out.println(expression.getValue());
    }

    @Test
    public void test2() {
        ExpressionParser parser = new SpelExpressionParser();

        String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
        Integer int1 = parser.parseExpression("1").getValue(Integer.class);
        Long long1 = parser.parseExpression("-1L").getValue(Long.class);
        Float float1 = parser.parseExpression("1.1").getValue(Float.class);
        Double double1 = parser.parseExpression("1.1E+2").getValue(Double.class);
        Integer hex1 = parser.parseExpression("0xa").getValue(Integer.class);
        Long hex2 = parser.parseExpression("0xaL").getValue(Long.class);
        Boolean true1 = parser.parseExpression("true").getValue(Boolean.class);
        Boolean false1 = parser.parseExpression("false").getValue(Boolean.class);
        Object null1 = parser.parseExpression("null").getValue(Object.class);

        System.out.println("str1=" + str1);
        System.out.println("int1=" + int1);
        System.out.println("long1=" + long1);
        System.out.println("float1=" + float1);
        System.out.println("double1=" + double1);
        System.out.println("hex1=" + hex1);
        System.out.println("hex2=" + hex2);
        System.out.println("true1=" + true1);
        System.out.println("false1=" + false1);
        System.out.println("null1=" + null1);
    }

    @Test
    public void test() {
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        log.info(message);

        exp = parser.parseExpression("6 mod 4");
        Integer value = exp.getValue(Integer.class);
        log.info("" + value);
    }

    @Test
    public void test3() {
        ExpressionParser parser = new SpelExpressionParser();
        Boolean v1 = parser.parseExpression("1>2").getValue(Boolean.class);
        // SpEL同样提供了等价的“EQ” 、“NE”、 “GT”、“GE”、 “LT” 、“LE”来表示等于、不等于、大于、大于等于、小于、小于等于，不区分大小写。
        Boolean v2 = parser.parseExpression("1 lt 2").getValue(Boolean.class);
        // 等于（==）、不等于(!=)、大于(>)、大于等于(>=)、小于(<)、小于等于(<=)，区间（between）运算。
        Boolean between1 = parser.parseExpression("1 between {1,2}").getValue(Boolean.class);
        System.out.println("v1=" + v1);
        System.out.println("v2=" + v2);
        System.out.println("between1=" + between1);
    }

    @Test
    public void test4() {
        ExpressionParser parser = new SpelExpressionParser();

        boolean result1 = parser.parseExpression("2>1 and (!true or !false)").getValue(boolean.class);
        boolean result2 = parser.parseExpression("2>1 && (!true || !false)").getValue(boolean.class);

        boolean result3 = parser.parseExpression("2>1 and (NOT true or NOT false)").getValue(boolean.class);
        boolean result4 = parser.parseExpression("2>1 && (NOT true || NOT false)").getValue(boolean.class);
        String result5 = parser.parseExpression("'Hello ' + 'World!'").getValue(String.class);

        System.out.println("result1=" + result1);
        System.out.println("result2=" + result2);
        System.out.println("result3=" + result3);
        System.out.println("result4=" + result4);
        System.out.println("result5=" + result5);
    }

    /**
     * 类类型表达式
     *
     * 使用“T(Type)”来表示java.lang.Class实例，“Type”必须是类全限定名，“java.lang”包除外， 即该包下的类可以不指定包名；使用类类型表达式还可以进行访问类静态方法及类静态字段。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:12
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void testClassTypeExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        // java.lang包类访问
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(result1);

        // 其他包类访问
        String expression2 = "T(com.nestor.springexpression.SpelTestOne.User)";
        Class<User> value = parser.parseExpression(expression2).getValue(Class.class);
        System.out.println(value == User.class);

        // 类静态字段访问
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        System.out.println(result3 == Integer.MAX_VALUE);

        // 类静态方法调用
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        System.out.println(result4);
    }

    /**
     * 类实例化
     *
     * 类实例化同样使用java关键字“new”，类名必须是全限定名，但java.lang包内的类型除外，如String、Integer。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:14
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void testConstructorExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("new String('路人甲java')").getValue(String.class);
        System.out.println(result1);

        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        System.out.println(result2);
    }

    /**
     * instanceof表达式
     *
     * SpEL支持instanceof运算符，跟Java内使用同义；如“'haha' instanceof T(String)”将返回true。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:14
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void testInstanceOfExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Boolean value = parser.parseExpression("'路人甲' instanceof T(String)").getValue(Boolean.class);
        System.out.println(value);
    }

    /**
     * 变量定义及引用
     *
     * 变量定义通过EvaluationContext接口的setVariable(variableName, value)
     * 方法定义；在表达式中使用"#variableName"引用；除了引用自定义变量，SpE还允许引用根对象及当前上下文对象，
     * 使用"#root"引用根对象，使用"#this"引用当前上下文对象；
     *
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:17
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void testVariableExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", "路人甲java");
        context.setVariable("lesson", "Spring系列");

        //获取name变量，lesson变量
        String name = parser.parseExpression("#name").getValue(context, String.class);
        System.out.println(name);
        String lesson = parser.parseExpression("#lesson").getValue(context, String.class);
        System.out.println(lesson);

        //StandardEvaluationContext构造器传入root对象，可以通过#root来访问root对象
        context = new StandardEvaluationContext("我是root对象");
        String rootObj = parser.parseExpression("#root").getValue(context, String.class);
        System.out.println(rootObj);

        //#this用来访问当前上线文中的对象
        String thisObj = parser.parseExpression("#this").getValue(context, String.class);
        System.out.println(thisObj);
    }

    @Test
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        //定义2个函数,registerFunction和setVariable都可以，不过从语义上面来看用registerFunction更恰当
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt1", parseInt);
        context.setVariable("parseInt2", parseInt);

        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("#parseInt1('3')").getValue(context, int.class));
        System.out.println(parser.parseExpression("#parseInt2('3')").getValue(context, int.class));

        String expression1 = "#parseInt1('3') == #parseInt2('3')";
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);
        System.out.println(result1);
    }

    @Test
    public void testAssignExpression1() {
        Object user = new Object() {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        };
        {
            //user为root对象
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext(user);
            parser.parseExpression("#root.name").setValue(context, "路人甲java");
            System.out.println(parser.parseExpression("#root").getValue(context, user.getClass()));
        }
        {
            //user为变量
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            context.setVariable("user", user);
            parser.parseExpression("#user.name").setValue(context, "路人甲java");
            System.out.println(parser.parseExpression("#user").getValue(context, user.getClass()));
        }
    }

    public static class Car {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class User {
        private Car car;

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "User{" +
                    "car=" + car +
                    '}';
        }
    }

    /**
     * 对象属性存取及安全导航表达式
     *
     * 对象属性获取非常简单，即使用如“a.property.property”这种点缀式获取，SpEL对于属性名首字母是不区分大小写的；SpEL还引入了Groovy语言中的安全导航运算符“(对象|属性)?.属性”，用来避免“?
     * .”前边的表达式为null时抛出空指针异常，而是返回null；修改对象属性值则可以通过赋值表达式或Expression接口的setValue方法修改。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:40
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void test5() {
        User user = new User();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", user);

        ExpressionParser parser = new SpelExpressionParser();
        //使用.符号，访问user.car.name会报错，原因：user.car为空
        try {
            System.out.println(parser.parseExpression("#user.car.name").getValue(context, String.class));
        } catch (EvaluationException | ParseException e) {
            System.out.println("出错了：" + e.getMessage());
        }
        //使用安全访问符号?.，可以规避null错误
        System.out.println(parser.parseExpression("#user?.car?.name").getValue(context, String.class));

        Car car = new Car();
        car.setName("保时捷");
        user.setCar(car);

        System.out.println(parser.parseExpression("#user?.car?.toString()").getValue(context, String.class));
    }

    /**
     *
     * Bean引用
     *
     * SpEL支持使用“@”符号来引用Bean，在引用Bean时需要使用BeanResolver接口实现来查找Bean，Spring提供BeanFactoryResolver实现。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:44
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void test6() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        User user = new User();
        Car car = new Car();
        car.setName("保时捷");
        user.setCar(car);
        factory.registerSingleton("user", user);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(factory));

        ExpressionParser parser = new SpelExpressionParser();
        User userBean = parser.parseExpression("@user").getValue(context, User.class);
        System.out.println(userBean);
        System.out.println(userBean == factory.getBean("user"));
    }

    /**
     * 内联List
     *
     * 从Spring3.0.4开始支持内联List，使用{表达式，……}定义内联List，如“{1,2,3}”将返回一个整型的ArrayList，而“{}”将返回空的List，对于字面量表达式列表，SpEL会使用java
     * .util.Collections.unmodifiableList方法将列表设置为不可修改。
     *
     * @param
     * @return void
     * @date : 2022/6/10 18:49
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void test7() {
        ExpressionParser parser = new SpelExpressionParser();
        //将返回不可修改的空List
        List<Integer> result2 = parser.parseExpression("{}").getValue(List.class);
        //对于字面量列表也将返回不可修改的List
        List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(List.class);
        Assert.assertEquals(new Integer(1), result1.get(0));
        try {
            result1.set(0, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //对于列表中只要有一个不是字面量表达式，将只返回原始List，
        //不会进行不可修改处理
        String expression3 = "{{1+2,2+4},{3,4+4}}";
        List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);
        result3.get(0).set(0, 1);
        System.out.println(result3);
        //声明二维数组并初始化
        int[] result4 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);
        System.out.println(result4[1]);
        //定义一维数组并初始化
        int[] result5 = parser.parseExpression("new int[1]").getValue(int[].class);
        System.out.println(result5[0]);
        int[][] result6 = parser.parseExpression("new int[][]{{1},{2},{3}}").getValue(int[][].class);
    }

    /**
     * 集合，字典元素访问
     *
     * SpEL目前支持所有集合类型和字典类型的元素访问，使用“集合[索引]”访问集合元素，使用“map[key]”访问字典元素；
     *
     * @param
     * @return void
     * @date : 2022/6/10 19:04
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Test
    public void testCollection() {
        //SpEL内联List访问
        int result1 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);
        System.out.println(result1);

        //SpEL目前支持所有集合类型的访问
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(1);
        collection.add(2);

        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("collection", collection);
        int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
        System.out.println(result2);

        //SpEL对Map字典元素访问的支持
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);

        EvaluationContext context3 = new StandardEvaluationContext();
        context3.setVariable("map", map);
        int result3 = parser.parseExpression("#map['a']").getValue(context3, int.class);
        System.out.println(result3);
    }



    // @Test
    // public void test1() {
    // Expression exp = parser.parseExpression("'Hello World'.concat('!')");
    // String message = exp.getValue(String.class);
    // log.info(message);
    //
    // exp = parser.parseExpression("'Hello World'.length()");
    // Integer size = exp.getValue(Integer.class);
    // log.info("" + size);
    //
    // exp = parser.parseExpression("'Hello World'.split(' ')[0]");
    // message = exp.getValue(String.class);
    // log.info(message);
    // }
    //
    //
    // @Test
    // public void test22() {
    // User user = new User("John", "Doe", true, "john.doe@acme.com",30);
    // Expression exp = parser.parseExpression("firstName");
    // log.info((String)exp.getValue(user));
    //
    // exp = parser.parseExpression("email.split('@')[0]");
    // String emailId = exp.getValue(user, String.class);
    // log.info(emailId);
    //
    // exp = parser.parseExpression("age");
    // Integer age = exp.getValue(user, Integer.class);
    // log.info("" + age);
    // }

    // @Test
    // public void test3() {
    // User user = new User("John", "Doe", true,"john.doe@acme.com", 30);
    // Expression exp = parser.parseExpression("age > 18");
    // log.info("" + exp.getValue(user,Boolean.class));
    // }

    // @Test
    // public void test4() {
    // User user = new User("John", "Doe", true, "john.doe@acme.com",30);
    // StandardEvaluationContext context = new StandardEvaluationContext();
    // context.setVariable("user", user);
    //
    // Expression exp = parser.parseExpression("#user.getAdmin()");
    // Boolean result = exp.getValue(context,Boolean.class);
    // log.info("" + result);
    // }
    //
    // @Test
    // public void test5() {
    // try {
    // StandardEvaluationContext context = new StandardEvaluationContext();
    // context.registerFunction("isURLValid",
    // StringHelper.class.getDeclaredMethod("isValid", new Class[] { String.class }));
    //
    // String expression = "#isURLValid('http://google.com')";
    //
    // Boolean isValid = parser.parseExpression(expression).getValue(context, Boolean.class);
    // log.info("" + isValid);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // @Test
    // public void test6() {
    //     log.info(user);
    // }

    public static class StringHelper {
        public static boolean isValid(String url) {
            return true;
        }
    }

    // public static class User {
    //     private String firstName;
    //     private String name1;
    //     private Boolean admin;
    //     private String email;
    //     private Integer age;
    //
    //     public User(String firstName, String name1, Boolean admin, String email, Integer age) {
    //         this.firstName = firstName;
    //         this.name1 = name1;
    //         this.admin = admin;
    //         this.email = email;
    //         this.age = age;
    //     }
    //
    //     public User() {
    //     }
    //
    //     public String getFirstName() {
    //         return firstName;
    //     }
    //
    //     public void setFirstName(String firstName) {
    //         this.firstName = firstName;
    //     }
    //
    //     public String getName1() {
    //         return name1;
    //     }
    //
    //     public void setName1(String name1) {
    //         this.name1 = name1;
    //     }
    //
    //     public Boolean getAdmin() {
    //         return admin;
    //     }
    //
    //     public void setAdmin(Boolean admin) {
    //         this.admin = admin;
    //     }
    //
    //     public String getEmail() {
    //         return email;
    //     }
    //
    //     public void setEmail(String email) {
    //         this.email = email;
    //     }
    //
    //     public Integer getAge() {
    //         return age;
    //     }
    //
    //     public void setAge(Integer age) {
    //         this.age = age;
    //     }
    //
    //     @Override
    //     public String toString() {
    //         return "User{" + "firstName='" + firstName + '\'' + ", name1='" + name1 + '\'' + ", admin=" + admin
    //                 + ", email='" + email + '\'' + ", age=" + age + '}';
    //     }
    // }
}
