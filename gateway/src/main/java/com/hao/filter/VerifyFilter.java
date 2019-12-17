package com.hao.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zuul过滤器
 */
@Component
public class VerifyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // 定义filter的类型，有pre、route、post、error四种
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 定义filter的顺序，数字越小表示顺序越高，越先执行
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        // 表示是否需要执行该filter，true表示执行，false表示不执行
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        // filter需要执行的具体操作
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        context.setSendZuulResponse(true);
        context.setResponseStatusCode(200);

        JSONObject json = new JSONObject();
        json.put("message", "验证失败！");
        context.setResponseBody(json.toJSONString());
        return null;
    }

}