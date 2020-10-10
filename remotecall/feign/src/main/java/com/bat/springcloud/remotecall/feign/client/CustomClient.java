package com.bat.springcloud.remotecall.feign.client;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.dto.CustomStructureDTO;
import com.bat.springcloud.remotecall.feign.client.fallback.CustomClientFallback;
import com.bat.springcloud.remotecall.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Client
 *
 * @author ZhengYu
 * @version 1.0 2020/4/17 15:16
 **/
@Primary
@FeignClient(value = "SERVICE-PROVIDER", configuration = FeignConfig.class, fallback = CustomClientFallback.class, path = "/provider/service")
public interface CustomClient {

    /**
     * 测试基础的feign功能
     *
     * @param resourceUuid 资源UUID
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/resource/{resourceUuid}")
    CommonResult getFeignStruct(@PathVariable String resourceUuid, @RequestParam(value = "param", required = false) String param);

    /**
     * 使用feign发送get请求的pojo对象
     *
     * @param customStructureDTO pojo
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping(value = "/feign/pojo")
    CommonResult feignGetPojo(CustomStructureDTO customStructureDTO);
}
