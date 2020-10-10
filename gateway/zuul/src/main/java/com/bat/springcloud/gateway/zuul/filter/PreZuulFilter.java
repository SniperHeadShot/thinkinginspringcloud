package com.bat.springcloud.gateway.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.bussiness.bean.dto.CommonResult;
import com.bat.springcloud.bussiness.bean.enums.ConstantEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Pre PreZuulFilter
 *
 * @author ZhengYu
 * @version 1.0 2020/5/21 14:42
 **/
@Component
public class PreZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("PreZuulFilter ...");

        // 校验请求头里是否有token
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String token = request.getHeader("token");
//        if (!verityToken(token)) {
//            // 禁止路由
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            ctx.setResponseBody(JSONObject.toJSONString(CommonResult.buildCommonResult(ConstantEnum.TOKEN_FAIL)));
//            ctx.getResponse().setCharacterEncoding(StandardCharsets.UTF_8.name());
//            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//            // 当有多个Filter的时候, 上级Filter通过后可以设置一个标志, 下级Filter可以根据标志来选择是否执行
//            ctx.set("checkFlag", false);
//        }
        return null;
    }

    private boolean verityToken(String token) {
        return StringUtils.isNotEmpty(token);
    }
}
