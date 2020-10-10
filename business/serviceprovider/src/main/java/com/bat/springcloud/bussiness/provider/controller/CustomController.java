package com.bat.springcloud.bussiness.provider.controller;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.dto.CustomStructureDTO;
import com.bat.springcloud.bussiness.bean.dto.FeignStruct;
import com.bat.springcloud.bussiness.bean.enums.ConstantEnum;
import com.bat.springcloud.bussiness.provider.controller.hystrix.CustomControllerHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 提供基础服务
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 19:04
 **/
@Slf4j
@RestController
@RequestMapping("/provider/service")
public class CustomController extends CustomControllerHystrix {

    @Value("${server.port}")
    private Integer serverPort;

    /**
     * 测试feign的基础功能
     * {@link CustomControllerHystrix#getFeignStructHystrix(String, String, HttpServletRequest, Throwable)}
     *
     * @param resourceUuid 资源UUID
     * @param param        参数
     * @return CommonResult
     * @author ZhengYu
     */
    @HystrixCommand(fallbackMethod = "getFeignStructHystrix")
    @GetMapping("/resource/{resourceUuid}")
    public CommonResult getFeignStruct(@PathVariable("resourceUuid") String resourceUuid, @RequestParam(value = "param", required = false) String param, HttpServletRequest request) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("surprise");
        }
        FeignStruct feignStruct = new FeignStruct();
        feignStruct.setResourceUuid(resourceUuid);
        feignStruct.setServerPort(serverPort);
        feignStruct.setParam(param);
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, feignStruct);
    }

    /**
     * 接收feign使用get请求发送的pojo对象
     *
     * @param customStructureDTO 接收参数的pojo
     * @return CommonResult
     * @author ZhengYu
     */
    @GetMapping("/feign/pojo")
    public CommonResult feignGetPojo(CustomStructureDTO customStructureDTO) {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, customStructureDTO);
    }

    /**
     * 接收feign发送的param和文件信息
     *
     * @param file  文件信息
     * @param name  参数
     * @param value 参数
     * @return CommonResult
     * @author ZhengYu
     */
    @PostMapping(value = "/feign/upload")
    public CommonResult feignUpload(@RequestPart(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "value", required = false) String value) {
        log.info("接收feign发送的param name=[{}], value=[{}], 文件名=[{}], 文件大小=[{}]", name, value, file.getOriginalFilename(), file.getSize());
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS);
    }
}
