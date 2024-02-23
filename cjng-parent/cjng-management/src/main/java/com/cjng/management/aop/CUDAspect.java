package com.cjng.management.aop;

import com.alibaba.fastjson.JSONObject;
import com.cjng.management.mapper.OperateLogMapper;
import com.cjng.management.pojo.OperateLog;
import com.cjng.management.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Description
 * Java实现的AOP（面向切面编程） aspect，用于记录对数据库进行增删改操作时的日志。
 */
@Slf4j
@Component
@Aspect
public class CUDAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;


    @Pointcut("@annotation(com.cjng.management.aop.CUDLog)")
    private void CUDpc() {
        //用于定义切点。这个方法没有实现任何逻辑，只是一个占位符，以便在around方法中使用相同的切点。
    }


    /**
     * @Description
     * 获取HTTP请求中的JWT（JSON Web Token），并将其解析为Claims对象，从Claims对象中获取用户ID（user ID）
     * 并将结果值转换为JSON字符串。最后，将OperateLog对象插入到数据库中。
     */
    @Around("CUDpc()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLog operateLog = new OperateLog();

        String jwt = httpServletRequest.getHeader("token");
        log.info("jwt: {}", jwt);
        Claims claims = JwtUtils.parseJWT(jwt);

        operateLog.setOperateUser((Integer) claims.get("id"));
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        Long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        operateLog.setCostTime(endTime - beginTime);
        operateLog.setReturnValue(JSONObject.toJSONString(result));
        operateLogMapper.insert(operateLog);
        return result;
    }
}
