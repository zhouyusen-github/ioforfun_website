package com.quickstart.dao.impl;

import com.quickstart.dao.UserDao;
import com.quickstart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public User findByUsername(String username) {
        User user;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (EmptyResultDataAccessException e) { // if queryForObject has no return
            user = null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,activation_code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                    user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getActivationCode()
                );
    }

    @Override
    public User findByActivationCode(String activationCode) {
        User user;
        try {
            String sql = "select * from tab_user where activation_code = ?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),activationCode);
        } catch (EmptyResultDataAccessException e) { // if queryForObject has no return
            user = null;
        }
        return user;
    }

    @Override
    public void updateStatus(User user, int status) {
        String sql = " update tab_user set status = ? where user_id= ? ";
        template.update(sql,status,user.getUserId());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (EmptyResultDataAccessException e) { // if queryForObject has no return
            user = null;
        }
        return user;
    }
}
