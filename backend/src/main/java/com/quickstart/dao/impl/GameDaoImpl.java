package com.quickstart.dao.impl;

import com.quickstart.dao.GameDao;
import com.quickstart.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int findTotalCount(int categoryId,String name) {
        // complete_sql: "select count(*) from tab_game where category_id = ? and name like ?";
        String sql = "select count(*) from tab_game where 1=1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params = new ArrayList();
        if(categoryId != 0){
            stringBuilder.append( " and category_id = ? ");
            params.add(categoryId);
        }
        if(name != null && name.length() > 0){
            stringBuilder.append(" and name like ? ");
            params.add("%"+name+"%");
        }
        sql = stringBuilder.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Game> findByPage(int categoryId, int start, int pageSize, String name) {
        // complete_sql: "select * from tab_game where category_id = ? and name like ? limit ? , ?";
        String sql = "select * from tab_game where 1 = 1 ";
        StringBuilder stringBuilder = new StringBuilder(sql);

        List params = new ArrayList();
        if(categoryId != 0){
            stringBuilder.append( " and category_id = ? ");
            params.add(categoryId);
        }
        if(name != null && name.length() > 0){
            stringBuilder.append(" and name like ? ");
            params.add("%"+name+"%");
        }
        stringBuilder.append(" limit ? , ? ");// page
        sql = stringBuilder.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Game>(Game.class),params.toArray());
    }

    @Override
    public Game findGameByGameId(int gameId) {
        String sql = "select * from tab_game where game_id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Game>(Game.class),gameId);
    }
}
