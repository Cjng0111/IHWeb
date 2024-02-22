package com.cjng.management.service;

import com.cjng.management.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    Dept getById(Integer id);
}
