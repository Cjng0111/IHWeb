package com.cjng.management.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.cjng.management.pojo.Result;
import com.cjng.management.utils.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * 处理登录拦截的Java拦截器，检查jwt
 * */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = ((HttpServletRequest) request).getRequestURL().toString();
        log.info("请求地址：{}", url);

        String jwt = ((HttpServletRequest) request).getHeader("token");
        if (StringUtil.isEmpty(jwt)) {
            log.info("jwt为空");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            ((HttpServletResponse) response).getWriter().write(notLogin);
            return false;
        }
        log.info("jwt不为空");
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            ((HttpServletResponse) response).getWriter().write(notLogin);
            return false;
        }

        log.info("放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("LoginCheckInterceptor.postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LoginCheckInterceptor.afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
