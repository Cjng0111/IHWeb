package com.cjng.management.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * 已经弃用的方式，原方式使用AOP记录执行时间
 * */
@Slf4j
@Component
@Aspect
@Order(1)
public class TimeAspect {
    @Pointcut("execution(* com.cjng.management.service.*.*(..)) ||"
            + "execution(* com.cjng.management.controller.*.*(..))")
//    @Pointcut("@annotation(com.cjng.management.aop.LogTime)")//在需要执行时间统计的方法上使用注解
    private void pc() {

    }

//    @Around("pc()")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        Long end = System.currentTimeMillis();

        log.info("{} 执行时长 {} 毫秒", joinPoint.getSignature(), end - start);

        return proceed;
    }
}
