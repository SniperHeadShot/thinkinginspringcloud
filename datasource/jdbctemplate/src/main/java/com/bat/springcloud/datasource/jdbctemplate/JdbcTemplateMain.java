package com.bat.springcloud.datasource.jdbctemplate;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import com.bat.springcloud.datasource.jdbctemplate.dao.CustomStructureDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhengYu
 * @version 1.0 2020/8/19 9:47
 **/
public class JdbcTemplateMain {
    public static void main(String[] args) {
        // 配置容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        applicationContext.refresh();

        CustomStructureDao customStructureDao = applicationContext.getBean(CustomStructureDao.class);
        // 插入记录
        Integer insertCustomStructureRows = customStructureDao.insertCustomStructure();
        System.out.println(String.format("插入操作执行结果: [%d]", insertCustomStructureRows));

        // 查询记录
        CustomStructurePO customStructurePO = customStructureDao.queryCustomStructureById(1L);
        System.out.println(String.format("查询结果执行结果: [%s]", JSONObject.toJSONString(customStructurePO)));

        applicationContext.close();
    }
}
