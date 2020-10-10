package com.bat.springcloud.datasource.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import com.bat.springcloud.datasource.mybatis.config.MybatisSpringConfig;
import com.bat.springcloud.datasource.mybatis.service.CustomStructureService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Mybatis 与 Spring 整合
 *
 * @author ZhengYu
 * @version 1.0 2020/8/19 17:13
 **/
public class MybatisSpringApplication {
    public static void main(String[] args) {
        // 配置容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Bean
        applicationContext.register(MybatisSpringConfig.class);
        applicationContext.refresh();

        CustomStructureService customStructureService = applicationContext.getBean(CustomStructureService.class);

        // 插入记录
        customStructureService.insertCustomStructure();

        // 查询记录
        CustomStructurePO customStructurePO = customStructureService.queryCustomStructureById(1L);
        System.out.println(JSONObject.toJSONString(customStructurePO));

        applicationContext.close();
    }
}
