package com.cjng.management.mapper;

import com.cjng.management.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface EmpMapper {
    //    @Select("select count(*) from emp")
//    Integer count();
//
//    @Select("select * from emp limit #{start},#{pageSize}")
//    List<Emp> page(Integer start, Integer pageSize);

//    @Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);

    void add(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);

    @Delete("delete from emp where dept_id=#{id}")
    void deleteByDeptId(Integer id);
}
