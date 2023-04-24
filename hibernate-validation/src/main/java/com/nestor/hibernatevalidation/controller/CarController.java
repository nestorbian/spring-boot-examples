package com.nestor.hibernatevalidation.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;

import com.nestor.hibernatevalidation.entity.CustomArg;
import com.nestor.hibernatevalidation.entity.ObjectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.hibernatevalidation.entity.Car;
import com.nestor.hibernatevalidation.entity.ListWrapper;
import com.nestor.hibernatevalidation.entity.ValidationList;
import com.nestor.hibernatevalidation.service.AbstractLogService;

@RestController
// @Controller
public class CarController {

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
    public Object addCar(@RequestBody @Valid ObjectWrapper<Car> car) {
        return "SUCCESS";
    }

    @PostMapping("/cars/wrapper")
    public Object addCar(@RequestBody @Valid ListWrapper<Car> car) {
        return "SUCCESS";
    }
}
