package com.bat.springcloud.datasource.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MybatisPlus
 *
 * @author ZhengYu
 * @version 1.0 2020/6/7 14:42
 **/
@SpringBootApplication
@MapperScan("com.bat.springcloud.datasource.mybatisplus.mapper")
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
