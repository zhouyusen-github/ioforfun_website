package com.quickstart.service.impl;

import com.quickstart.dao.UserDao;
import com.quickstart.domain.User;
import com.quickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String name;

    @Value("${activationDomain.ip}")
    private String ip;

    @Value("${activationDomain.port}")
    private String port;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean registerUser(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            return false;
        }
        user.setActivationCode(UUID.randomUUID().toString());
        user.setStatus(0);
        userDao.save(user);

        // set email
        String content="http://"+ip+":"+port+"/activation?activationCode="+user.getActivationCode()+" Open the link in browser to activate your ioforfun account";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(name);
        message.setTo(user.getEmail());
        message.setSubject("Activation Email");
        message.setText(content);
        emailSender.send(message);

        return true;
    }

    @Override
    public boolean activateUser(String activationCode) {
        User user = userDao.findByActivationCode(activationCode);
        if(user != null){
            userDao.updateStatus(user,1);
            return true;
        }else{
            return false;
        }
    }

    public User getUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }
}
