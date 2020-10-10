package com.bat.springcloud.remotecall.ribbon.controller;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.util.UuidUtils;
import com.bat.springcloud.remotecall.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ribbon Controller
 *
 * @author ZhengYu
 * @version 1.0 2020/5/21 0:06
 **/
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/base")
    public CommonResult getRibbonResult() {
        return ribbonService.ribbonBase(UuidUtils.getRandomUuid(), UuidUtils.getRandomUuid());
    }
}
