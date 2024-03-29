package com.cjng.management.controller;

import com.cjng.management.pojo.Emp;
import com.cjng.management.pojo.Result;
import com.cjng.management.service.EmpService;
import com.cjng.management.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * 使用Spring框架的RESTful API控制器
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("emp:{}", emp);
        Emp e = empService.login(emp);
        if (e != null) {
            Map<String, Object> claims=new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            log.info("jwt解码:{}", JwtUtils.parseJWT(jwt));
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
