package com.cse.haste.controller;

import com.alibaba.fastjson.JSON;
import com.cse.haste.model.pojo.User;
import com.cse.haste.util.Constant;
import com.cse.haste.util.GuavaCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author WangZhenqi
 */
public class HasteFacade {

    private final Logger logger = LoggerFactory.getLogger(HasteFacade.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession session;

    HttpServletRequest getRequest() {
        return request;
    }

    HttpServletResponse getResponse() {
        return response;
    }

    HttpSession getHttpSession() {
        return session;
    }

    void setCurrentUser(User user) {
        String token = user.getToken();
        GuavaCacheUtil.setOne(token, JSON.toJSONString(user));
    }

    User getCurrentUser() {
        String token = request.getHeader(Constant.TOKEN);
        String userStr = GuavaCacheUtil.getOne(token);
        return JSON.parseObject(userStr, User.class);
    }

    void removeCurrentUser() {
        String token = request.getHeader(Constant.TOKEN);
        GuavaCacheUtil.removeOne(token);
    }
}
