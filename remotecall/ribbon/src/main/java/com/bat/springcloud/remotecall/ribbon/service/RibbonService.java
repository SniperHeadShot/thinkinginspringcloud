package com.bat.springcloud.remotecall.ribbon.service;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon Service
 *
 * @author ZhengYu
 * @version 1.0 2020/5/21 0:05
 **/
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    public CommonResult ribbonBase(String resourceUuid, String param) {
        return restTemplate.getForObject(String.format("http://SERVICE-PROVIDER/provider/service/resource/%s?param=%s", resourceUuid, param), CommonResult.class);
    }
}
