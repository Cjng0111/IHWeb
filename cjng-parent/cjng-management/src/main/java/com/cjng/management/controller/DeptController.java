package com.cjng.management.controller;

import com.cjng.management.aop.CUDLog;
import com.cjng.management.pojo.Dept;
import com.cjng.management.pojo.Result;
import com.cjng.management.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
@Scope("prototype")// 原型模式，实现多例
@Lazy// 懒加载
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("查询全部部门信息");
        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    @CUDLog
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除部门信息，id={}", id);
        deptService.delete(id);
        return Result.success();
    }

    @CUDLog
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门信息:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询部门信息，id={}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }


    @CUDLog
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("更新部门信息:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}


