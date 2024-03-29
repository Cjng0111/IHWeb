package com.cjng.management.mapper;

import com.cjng.management.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * 记录关于dept的操作，已被弃用
 * */
@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(create_time,description) values(#{createTime},#{description})")
    void insert(DeptLog deptLog);
}
