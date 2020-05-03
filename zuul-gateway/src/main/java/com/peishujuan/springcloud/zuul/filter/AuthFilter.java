package com.peishujuan.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends ZuulFilter {
    /**
     * filterType：返回一个字符串代表过滤器的类型，
     * 在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 在Zuul网关里可以有多个过滤器，并且可以过滤顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 添加是否过滤的条件
     * false：啥也不过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //业务逻辑判断,首先获取request对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的URl
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //放行User的请求
        if (requestURI.startsWith("/user")){
            return false;
        }
        return true;
    }

    /**
     * 执行具体的过滤逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //业务逻辑判断,首先获取request对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("run:"+request.getRequestURI());
        //验证是否授权
        String token = request.getParameter("token");
        String refer = request.getHeader("refer");
        //判断是否有token，而且是否是登录时候给到客户端（Vue）
        if(StringUtils.isEmpty(token)){
            //设置为false，就不会路由到后端的服务
            requestContext.setSendZuulResponse(false);
            //设置http状态码，401标识未授权
            requestContext.setResponseStatusCode(401);
            //返回响应信息
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write("{\"code\":401,\"msg:\":\"未授权呢\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}