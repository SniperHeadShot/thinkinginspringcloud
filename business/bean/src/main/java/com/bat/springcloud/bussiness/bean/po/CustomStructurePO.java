package com.bat.springcloud.bussiness.bean.po;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * 用于测试的结构体
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 17:32
 **/
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomStructurePO implements Serializable {

    Long id;

    String name;

    Integer age;

    public CustomStructurePO(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public CustomStructurePO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}