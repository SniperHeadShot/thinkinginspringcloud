package com.bat.springcloud.bussiness.bean.dto;

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
public class CustomStructureDTO implements Serializable {

    Long longField;

    String stringField;

    Integer integerField;
}