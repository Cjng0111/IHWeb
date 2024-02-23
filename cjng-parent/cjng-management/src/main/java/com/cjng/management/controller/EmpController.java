package com.cjng.management.controller;

import com.cjng.management.aop.CUDLog;
import com.cjng.management.pojo.Emp;
import com.cjng.management.pojo.PageBean;
import com.cjng.management.pojo.Result;
import com.cjng.management.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:
 * 使用Spring框架的RESTful API控制器
 */
@Slf4j
@RestController
@RequestMapping("/emps")
@Scope("prototype")// 原型模式，实现多例
@Lazy// 懒加载
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询员工信息,参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @CUDLog
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除员工信息,参数:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @CUDLog
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工信息,参数:{}", emp);
        empService.add(emp);
        return Result.success(emp);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息,参数:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @CUDLog
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息,参数:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}

