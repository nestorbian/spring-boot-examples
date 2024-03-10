package com.nestor.hibernatevalidation.controller;

import com.nestor.hibernatevalidation.entity.Car;
import com.nestor.hibernatevalidation.entity.ListWrapper;
import com.nestor.hibernatevalidation.entity.ObjectWrapper;
import com.nestor.hibernatevalidation.entity.TestAnnotationDTO;
import com.nestor.hibernatevalidation.entity.ValidationList;
import com.nestor.hibernatevalidation.schedule.ScheduleTest;
import com.nestor.hibernatevalidation.service.AbstractLogService;
import com.nestor.hibernatevalidation.util.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Validated(Car.Limit.class)
@Slf4j
// @DependsOn("logServiceImpl")
// @Controller
public class CarController implements InitializingBean {

    @Autowired
    private Validator validator;

    @Autowired
    private AbstractLogService logService;

    @GetMapping("/cars/${name}")
    public String placeholderCar() {
        System.out.println("命中/cars/${name}");
        return "SUCCESS";
    }

    @GetMapping("/cars")
    public String addCar() {
        logService.log("test");
        return "SUCCESS";
    }


    @GetMapping("/test-annotation")
    public TestAnnotationDTO testAnnotation(){
        TestAnnotationDTO testAnnotationDTO = new TestAnnotationDTO();
        testAnnotationDTO.setPhone("17677772345");
        testAnnotationDTO.setCustom("111111111111111111");
        testAnnotationDTO.setEmail("1433926101@qq.com");
        testAnnotationDTO.setIdCard("4444199810015555");
        //11位手机号
        log.info("mobile={}", "13511114444");
        log.info("mobile={},手机号：{}", "13511112222", "13511113333");
        log.info("手机号：{}", "13511115555");
        //固定电话（带区号-）
        log.info("tel：{},座机={}", "0791-83376222", "021-88331234");
        log.info("tel：{}", "0791-83376222");
        log.info("座机={}", "021-88331234");

        //地址
        log.info("address：{}", "浙江省杭州市西湖区北京西路100号");
        log.info("地址：{}", "上海市浦东区北京东路1-10号");

        //19位卡号
        log.info("cardNo：{}", "6227002020000101222");

        //姓名
        log.info("name={}, 姓名=[{}]，name={}，姓名：{}", "张三", "上官婉儿", "李云龙", "楚云飞");

        //密码
        log.info("password：{}，密码={}", "123456", "456789");
        log.info("password：{}", "123456");
        log.info("密码={}", "123456");

        //身份证号码
        log.info("idCard：{}，身份证号={}", "360123202111111122", "360123202111111122");
        log.info("身份证号={}", "360123202111111122");

        //邮箱
        log.info("邮箱:{}", "wxyz123@qq.com");
        log.info("email={}", "wxyz123@qq.com");
        return testAnnotationDTO;
    }

    @GetMapping("/cars/response")
    public void resp(HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        writer.write("/cars/response return...");
        response.flushBuffer(); // 后续servlet容器中响应给前端
        // writer.close();// 直接响应给前端
    }

    @PostMapping("/cars/request")
    public String resp(@RequestBody String arg) throws Exception {
        // json传参 通过自定义参数解析器解析 出CustomArg
        System.out.println(arg);
        return "SUCCESS";
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody @Validated(Car.Limit.class) Car car) {
        return car;
    }

    @PostMapping("/cars/map")
    public Object addCar(@RequestBody @Validated ValidationList<Car> car) {
        // 检验失败抛出NotReadablePropertyException
        return car;
    }

    @PostMapping("/cars/ObjectWrapper")
    @Validated
    public Object addCar(@RequestBody @Valid ObjectWrapper<Car> car) {
        System.err.println("/cars/ObjectWrapper");
        return car;
    }

    @PostMapping("/cars/wrapper")
    public Object addCar(@RequestBody @Valid ListWrapper<Car> car) {
        return "SUCCESS";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("1");
    }

    @GetMapping("/testRequired")
    public String testRequired(@RequestParam String name, Locale locale) {
        System.out.println("locale = " + locale);
        return "SUCCESS";
    }

    @GetMapping("/testDate2")
    public String testDate2(@RequestParam Date date) {
        System.out.println("date = " + date);
        return "SUCCESS";
    }

    @GetMapping("/testLocalDateTime2")
    public String testLocalDateTime2(@RequestParam LocalDateTime date) {
        System.out.println("date = " + date);
        return "SUCCESS";
    }

    @GetMapping("/testDate")
    public String testDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        System.out.println("date = " + date);
        return "SUCCESS";
    }

    @GetMapping("/testLocalDateTime")
    public String testLocalDateTime(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        System.out.println("date = " + date);
        return "SUCCESS";
    }

    @GetMapping("/errors")
    public Object error() {
        int i = 1 / 0;
        return "SUCCESS";
    }

    @GetMapping("/schedule")
    public Object schedule() {
        // 根据从spring上下文中获取ScheduleTest的bean
        ApplicationContextUtils.getApplicationContext().getBean(ScheduleTest.class);
        return "SUCCESS";
    }

    public static void main(String[] args) throws Exception {
        // DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        // System.out.println(LocalDateTime.now().format(formatter));

        int b = -9;
        System.out.printf("%03d%n", b);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //开启异步任务1
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务1，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 1;
            System.out.println("异步任务1结束");
            return result;
        }, executorService);

        //开启异步任务2
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务2，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 2;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务2结束");
            return result;
        }, executorService);

        //开启异步任务3
        CompletableFuture<Integer> task3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务3，当前线程是：" + Thread.currentThread().getId());
            int result = 1 + 3;
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务3结束");
            return result;
        }, executorService);

        //任务组合
        CompletableFuture<Void> allOf = CompletableFuture.allOf(task, task2, task3);

        //等待所有任务完成
        allOf.get();
        //获取任务的返回结果
        System.out.println("task结果为：" + task.get());
        System.out.println("task2结果为：" + task2.get());
        System.out.println("task3结果为：" + task3.get());
    }
}
