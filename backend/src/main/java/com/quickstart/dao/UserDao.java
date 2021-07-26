package com.quickstart.dao;

import com.quickstart.domain.User;

public interface UserDao {

    public User findByUsername(String username);

    public void save(User user);

    User findByActivationCode(String activationCode);

    void updateStatus(User user, int status);

    User findByUsernameAndPassword(String username, String password);
}
