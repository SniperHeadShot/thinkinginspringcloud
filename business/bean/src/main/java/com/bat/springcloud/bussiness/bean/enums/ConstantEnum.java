package com.bat.springcloud.bussiness.bean.enums;


import com.bat.springcloud.bussiness.bean.annotation.CodeEntity;

/**
 * 返回值枚举
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public enum ConstantEnum implements CodeEnumInterface {

    // 全局成功
    @CodeEntity(
            success = true,
            errCode = 0,
            msg = "操作成功!"
    )
    GLOBAL_SUCCESS,

    // 全局失败
    @CodeEntity(
            success = false,
            errCode = -1,
            msg = "系统繁忙，请稍后再试!"
    )
    GLOBAL_FAIL,

    // Token 无效
    @CodeEntity(
            success = false,
            errCode = 1000,
            msg = "没有token或者token无效"
    )
    TOKEN_FAIL,

    // 全局参数不完整
    @CodeEntity(
            success = false,
            errCode = 4000,
            msg = "参数不完整，请检查后再试!"
    )
    PARAMETER_VERIFICATION_FAIL,

    // 全局数据库执行失败
    @CodeEntity(
            success = false,
            errCode = 5000,
            msg = "数据库执行SQL失败!"
    )
    SQL_EXECUTE_FAIL,

    // 远程调用服务失败
    @CodeEntity(
            success = false,
            errCode = 6000,
            msg = "微服务不可用!"
    )
    REMOTE_CALL_FAIL,

    // 服务异常导致的熔断
    @CodeEntity(
            success = false,
            errCode = 7000,
            msg = "触发熔断!"
    )
    TRIGGER_HYSTRIX,

    // 惊喜的异常
    @CodeEntity(
            success = false,
            errCode = 9999,
            msg = "Surprise!"
    )
    GLOBAL_SURPRISE
}
