package com.nestor.hibernatevalidation.controller;

import com.nestor.hibernatevalidation.entity.Car;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CarController {

    @PostMapping("/cars")
    public Car addCar(@RequestBody @Validated(Car.Limit.class) Car car) {
        return car;
    }

}
