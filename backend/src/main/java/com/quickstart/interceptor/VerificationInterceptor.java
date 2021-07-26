package com.quickstart.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerificationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //验证校验
        boolean VerificationResult = checkVerificationCode(request);
        if(VerificationResult){
            return true;
        } else {
            request.getRequestDispatcher("/error/" + "verificationCodeWrong").forward(request,response);
            return false;
        }
    }

    public boolean checkVerificationCode(HttpServletRequest request) {
        String verificationCodeFrontend = request.getParameter("verificationCode");
        HttpSession session = request.getSession();
        String verificationCodeBackend = (String) session.getAttribute("verificationCodeRecord");
        session.removeAttribute("verificationCodeRecord");//为了保证验证码只能使用一次
        if(verificationCodeBackend == null || !verificationCodeBackend.equalsIgnoreCase(verificationCodeFrontend)){
            return false;
        } else {
            return true;
        }
    }
}
