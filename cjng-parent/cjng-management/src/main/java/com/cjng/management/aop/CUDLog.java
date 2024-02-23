package com.cjng.management.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//使用@Retention注解来指定注解的保留时间。RetentionPolicy.RUNTIME表示注解在运行时有效。
@Target(ElementType.METHOD)
//使用@Target注解来指定注解可以应用到的元素类型。ElementType.METHOD表示注解可以应用到方法上。
public @interface CUDLog {
    //这个注解并不会在编译时或者运行时执行任何操作。它的主要作用是标记和生成日志记录。
}
