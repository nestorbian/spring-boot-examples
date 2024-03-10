package com.nestor.hibernatevalidation.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理器
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/10/5
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus
    public String handleException(MethodArgumentNotValidException e, Locale locale) {
        BindingResult result = e.getBindingResult();
        for (ObjectError globalError : result.getGlobalErrors()) {
            return messageSource.getMessage(globalError, locale);
        }
        for (FieldError error : result.getFieldErrors()) {
            // 不使用defaultMessage，而是通过FieldError去获取国际化msg，
            // FieldError本质上是MessageSourceResolvable，可以结合MessageSource使用
            return messageSource.getMessage(error, locale);
        }

        return result.toString();
    }

}
