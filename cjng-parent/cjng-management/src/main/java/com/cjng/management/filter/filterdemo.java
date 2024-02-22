package com.cjng.management.filter;


import com.alibaba.fastjson.JSONObject;
import com.cjng.management.pojo.Result;
import com.cjng.management.utils.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class filterdemo implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURL().toString();
        log.info("请求地址：{}", url);
        if (url.contains("/login")) {
            log.info("登录页面，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String jwt = httpServletRequest.getHeader("token");
        if (StringUtil.isEmpty(jwt)) {
            log.info("jwt为空");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return;
        }
        log.info("jwt不为空");
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
