package com.bat.springcloud.remotecall.feign.client.fallback;

import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.dto.CustomStructureDTO;
import com.bat.springcloud.bussiness.bean.enums.ConstantEnum;
import com.bat.springcloud.remotecall.feign.client.CustomClient;
import org.springframework.stereotype.Component;

/**
 * CustomClient Fallback
 *
 * @author ZhengYu
 * @version 1.0 2020/5/19 10:16
 **/
@Component
public class CustomClientFallback implements CustomClient {

    /**
     * 测试基础的feign功能
     *
     * @param resourceUuid 资源UUID
     * @param param        参数
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @Override
    public CommonResult getFeignStruct(String resourceUuid, String param) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }

    @Override
    public CommonResult feignGetPojo(CustomStructureDTO customStructureDTO) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }
}
