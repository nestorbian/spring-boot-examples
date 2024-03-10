package com.nestor.springactuatordemo;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试@RestControllerEndpoint
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/12/25
 */
@Controller
@RestControllerEndpoint(id = "myRestControllerEndPoint")
@RequestMapping("/actuator/myRestControllerEndPoint")
public class MyRestControllerEndPoint {

    private String[] strs = new String[2];

    {
        strs[0] = "a";
        strs[1] = "b";
    }

    @GetMapping("")
    public String[] list() {
        return strs;
    }

    @PostMapping("{index}")
    public String add(@PathVariable int index, String value) {
        strs[index] = value;
        return "success";
    }

    @DeleteMapping("{index}")
    public String delete(@PathVariable int index) {
        strs[index] = null;
        return "success";
    }
}
