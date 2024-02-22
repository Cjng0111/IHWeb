package com.cjng.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id; //ID
    private LocalDateTime createTime; //创建时间
    private String description ; //修改时间
}
