package com.quickstart.dao.impl;

import com.quickstart.dao.DeveloperDao;
import com.quickstart.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeveloperDaoImpl implements DeveloperDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Developer findById(int id) {
        String sql = "select * from tab_developer where developer_id = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Developer>(Developer.class),id);
    }
}
