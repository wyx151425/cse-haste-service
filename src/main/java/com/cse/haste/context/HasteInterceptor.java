package com.cse.haste.context;

import com.alibaba.fastjson.JSON;
import com.cse.haste.model.dto.Response;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GuavaCacheUtil;
import com.cse.haste.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author WangZhenqi
 */
public class HasteInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(HasteInterceptor.class);

    private static final String API_USER_LOGIN = "%s/api/users/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("MetaInterceptor: preHandle");
        logger.info("User Address: " + request.getRemoteHost());
        logger.info("Request URL: " + request.getRequestURL().toString());
        logger.info("Request Method: " + request.getMethod());

        /* 1.请求相关数据 操作 */
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        /* 2.非拦截路由 检查 */
        if (String.format(API_USER_LOGIN, contextPath).equals(uri)) {
            if (null == request.getHeader(Constant.Request.Header.REFERER)) {
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(new Response<>(StatusCode.REQUEST_ILLEGAL, "REQUEST_ILLEGAL")));
                out.flush();
                return false;
            } else {
                return true;
            }
        }

        /* 3.Token 检查 */
        String token = request.getHeader(Constant.TOKEN);
        if (null == token) {
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(new Response<>(StatusCode.USER_LOGIN_TIMEOUT, "LOGIN_TIMEOUT")));
            out.flush();
            return false;
        } else {
            String userStr = GuavaCacheUtil.getOne(token);
            if (null == userStr) {
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(new Response<>(StatusCode.USER_LOGIN_TIMEOUT, "LOGIN_TIMEOUT")));
                out.flush();
                return false;
            } else {
                return true;
            }
        }
    }
}
