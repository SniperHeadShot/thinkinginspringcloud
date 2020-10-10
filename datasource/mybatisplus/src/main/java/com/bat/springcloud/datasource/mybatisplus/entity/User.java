package com.bat.springcloud.datasource.mybatisplus.entity;

import lombok.Data;

/**
 * User
 *
 * @author ZhengYu
 * @version 1.0 2020/6/7 14:56
 **/
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}
