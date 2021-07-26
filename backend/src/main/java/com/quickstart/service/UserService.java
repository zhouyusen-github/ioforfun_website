package com.quickstart.service;

import com.quickstart.domain.User;

public interface UserService {

    boolean registerUser(User user);

    boolean activateUser(String activationCode);

    User getUser(String username, String password);
}
