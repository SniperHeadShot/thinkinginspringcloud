package com.bat.springcloud.bussiness.bean.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Feign 模块测试结构体
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 19:05
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeignStruct {

    /**
     * 某个资源的ID
     */
    String resourceUuid;

    /**
     * 服务的端口
     */
    Integer serverPort;

    /**
     * param
     */
    String param;
}
