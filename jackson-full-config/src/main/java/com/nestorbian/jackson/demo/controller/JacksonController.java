package com.nestorbian.jackson.demo.controller;

import com.nestorbian.jackson.demo.enums.CurrCode;
import com.nestorbian.jackson.demo.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacksonController {

    @GetMapping(path = "/test")
    public Param getTest() {
        Param param = new Param();
        param.setCurrCode(CurrCode.RMB);
        return param;
    }

}
