package com.qf.interceptor;

import com.qf.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.qf.constant.SsmConstant.LOGIN_UI;
import static com.qf.constant.SsmConstant.USER;

/**
 * Administrator 2019/7/17 0017 15:02
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    //执行controller方法之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        //通过session获得用户信息
        User user=(User) session.getAttribute(USER);
        if(user==null){
            response.sendRedirect(request.getContextPath()+LOGIN_UI);
            return false;
        }else {
            return true;
        }
    }
    //执行完controller之后,返回ModelAndView之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //响应数据之前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
