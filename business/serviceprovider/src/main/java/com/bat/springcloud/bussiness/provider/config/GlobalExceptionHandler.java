package com.bat.springcloud.bussiness.provider.config;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 *
 * @author ZhengYu
 * @version 1.0 2020/6/1 14:08
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        if (e != null) {
            log.error("Exception ==> url:{}, {} {}", url, e.getMessage(), e);
        }
        return CommonResult.buildCommonResult(false, -1, "Global Exception Handler");
    }
}
