package com.bat.springcloud.bussiness.provider.controller.hystrix;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.enums.ConstantEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * CustomController Hystrix
 *
 * @author ZhengYu
 * @version 1.0 2020/6/1 14:23
 **/
public abstract class CustomControllerHystrix {

    protected CommonResult getFeignStructHystrix(String resourceUuid, String param, HttpServletRequest request, Throwable throwable) {
        return CommonResult.buildCommonResult(ConstantEnum.TRIGGER_HYSTRIX, String.format("资源[%s]不存在, 原因:[%s]", resourceUuid, throwable.getMessage()));
    }
}
