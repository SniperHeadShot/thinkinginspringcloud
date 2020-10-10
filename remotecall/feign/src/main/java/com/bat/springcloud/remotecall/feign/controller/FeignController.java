package com.bat.springcloud.remotecall.feign.controller;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.dto.CustomStructureDTO;
import com.bat.springcloud.bussiness.bean.enums.ConstantEnum;
import com.bat.springcloud.bussiness.bean.util.UuidUtils;
import com.bat.springcloud.remotecall.feign.client.CustomClient;
import com.bat.springcloud.remotecall.feign.client.CustomFileUploadClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

/**
 * Feign Controller
 *
 * @author ZhengYu
 * @version 1.0 2020/4/17 15:21
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private CustomClient customClient;

    @Autowired
    private CustomFileUploadClient customFileUploadClient;

    @GetMapping("/resource/{resourceUuid}")
    @HystrixCommand(fallbackMethod = "hystrixGetFeignStruct")
    public CommonResult getFeignStruct(@PathVariable String resourceUuid) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Surprise");
        }
        return customClient.getFeignStruct(resourceUuid, UuidUtils.getRandomUuid());
    }

    /**
     * 服务熔断使用
     *
     * @param resourceUuid 资源UUID
     * @return com.bat.springcloud.bussiness.bean.dto.CommonResult
     * @author ZhengYu
     */
    public CommonResult hystrixGetFeignStruct(@PathVariable String resourceUuid) {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, String.format("[%s]不存在", resourceUuid), null);
    }

    @GetMapping("/pojo")
    public CommonResult feignGetPojo(CustomStructureDTO customStructureDTO) {
        return customClient.feignGetPojo(customStructureDTO);
    }

    @PostMapping(value = "/upload")
    public CommonResult feignUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "value", required = false) String value) {
        return customFileUploadClient.feignUpload(file, name, value);
    }
}
