package com.nestor.mybatisdemo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/12/4
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public Map<String, String> handleException(Exception e) {
        log.error("发生异常:", e);
        Map<String, String> map = new HashMap<>(16);
        map.put("code", "0001");
        map.put("msg", e.getMessage());
        return map;
    }

}
