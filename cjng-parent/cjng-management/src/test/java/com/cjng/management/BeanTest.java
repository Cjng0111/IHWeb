package com.cjng.management;

import com.cjng.management.controller.DeptController;
import com.cjng.management.pojo.Result;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeanTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        DeptController bean1 = applicationContext.getBean(DeptController.class);
        DeptController bean2 = (DeptController) applicationContext.getBean("deptController");
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean3);
    }

    @Autowired
    private Gson gson;

    @Test
    public void testGson() {
        System.out.println(gson.toJson(Result.success()));
    }
}
