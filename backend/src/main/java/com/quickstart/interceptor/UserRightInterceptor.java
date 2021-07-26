package com.quickstart.interceptor;

import com.quickstart.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRightInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/error/" + "notlogin").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
