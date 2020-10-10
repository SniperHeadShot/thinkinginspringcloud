package com.bat.springcloud.bussiness.provider.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author ZhengYu
 * @version 1.0 2020/9/26 11:55
 **/
@RestController
public class TestController {

    @PostMapping("/dp/face/capture/upload")
    public void test(@RequestBody Object object) {
        System.out.println("===============");
    }

    @PostMapping("/api/v1/camera/keepalive")
    public void keepAlive(@RequestBody Object object){
        System.out.println("===============");
    }
}

