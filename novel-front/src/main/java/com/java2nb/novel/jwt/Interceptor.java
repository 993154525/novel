package com.java2nb.novel.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaotian
 * @create 2020-11-29 10:55
 */
@Slf4j
public class Interceptor implements HandlerInterceptor {

    /**
     * 这里可以对请求之前进行拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session里面有没有用户
        String loginUser = (String) request.getSession().getAttribute("username");
        //如果没有 转发到首页登录
        if (loginUser == null) {
            response.sendRedirect("http://shaotian.link:9080/config/login");
//            request.getRequestDispatcher("config/login").forward(request, response);
            return false;
        } else {
            log.info("session User 登录用户:" + loginUser);
            return true;
        }
    }
}