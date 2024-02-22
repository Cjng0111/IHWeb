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
    }

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
