package com.quickstart.controller;

import com.quickstart.domain.ResultInfo;
import com.quickstart.domain.User;
import com.quickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultInfo register(@RequestBody User user) {
        boolean flag = service.registerUser(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("register fail!");
        }
        return info;
    }

    @RequestMapping(value = "/activation",method = RequestMethod.PUT)
    public ResultInfo activateUser(@RequestParam("activationCode") String activationCode) {
        ResultInfo info = new ResultInfo();
        boolean flag = false;
        if(activationCode != null){
            flag = service.activateUser(activationCode);
            info.setFlag(flag);
        }
        info.setFlag(flag);
        return info;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public User getLoginUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user;
    }

    @RequestMapping(value = "/session" , method = RequestMethod.GET)
    public ResultInfo login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session){
        User u  = service.getUser(username, password);
        ResultInfo info = new ResultInfo();
        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("username or password is wrong");
        } else {
            if (u.getStatus()==0) {
                info.setFlag(false);
                info.setErrorMsg("Your account is not activated, please activate");
            } else if (u.getStatus()==2){
                info.setFlag(false);
                info.setErrorMsg("Your account is banned");
            } else if (u.getStatus()==1) { // login success
                info.setFlag(true);
                session.setAttribute("user",u);
            }
        }
        return info;
    }

    @RequestMapping(value = "/session" , method = RequestMethod.DELETE)
    public ResultInfo exit(HttpSession session) {
        session.invalidate();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        return info;
    }
}
