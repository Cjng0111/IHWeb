package com.cjng.management.service.impl;

import com.cjng.management.mapper.DeptMapper;
import com.cjng.management.mapper.EmpMapper;
import com.cjng.management.pojo.Dept;
import com.cjng.management.pojo.DeptLog;
import com.cjng.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogServiceImpl deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
//        try {
            deptMapper.deleteById(id);
            empMapper.deleteByDeptId(id);
//        } finally {
//            DeptLog deptLog = new DeptLog();
//            deptLog.setCreateTime(LocalDateTime.now());
//            deptLog.setDescription("删除部门" + id);
//            deptLogService.insert(deptLog);
//        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
}
