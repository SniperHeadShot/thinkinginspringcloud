package com.bat.springcloud.gateway.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;

/**
 * RouteZuulFilter
 *
 * @author ZhengYu
 * @version 1.0 2020/5/21 14:42
 **/
@Component
public class RouteZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ROUTE_TYPE;
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
        System.out.println("RouteZuulFilter ...");
        return null;
    }
}
