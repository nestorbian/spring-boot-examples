package com.nestor.mybatisdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/7/9
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class SimpleAspect {

    @Pointcut("execution(* com.nestor.mybatisdemo.service..*.*(..))")
    public void a() {

    }

    @Around("a()")
    public Object b(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("pre..." + joinPoint.getSignature().getDeclaringTypeName() + "."+joinPoint.getSignature().getName());
        Object o = joinPoint.proceed();
        System.out.println("end..." + joinPoint.getSignature().getDeclaringTypeName() + "."+joinPoint.getSignature().getName());
        return o;
    }

}
