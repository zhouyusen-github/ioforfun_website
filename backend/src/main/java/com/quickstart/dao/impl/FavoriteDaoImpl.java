package com.quickstart.dao.impl;

import com.quickstart.dao.FavoriteDao;
import com.quickstart.domain.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FavoriteDaoImpl implements FavoriteDao {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Favorite findByGameIdAndUserId(int gameId, int userId) {
        Favorite favorite = null;
        try {
            String sql = " select * from tab_favorite where game_id = ? and user_id = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), gameId, userId);
        } catch (EmptyResultDataAccessException e) { // if queryForObject has no return
            favorite = null;
        }
        return favorite;
    }

    @Override
    public List<Favorite> findByUserId(int start,int pageSize, int userId) {
        List<Favorite> favoriteList = null;
        String sql = " select * from tab_favorite where user_id = ? limit ? , ?";
        favoriteList = template.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), userId,start,pageSize);
        return favoriteList;
    }

    @Override
    public int findCountByGameId(int gameId) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE game_id = ?";

        return template.queryForObject(sql,Integer.class,gameId);
    }

    @Override
    public void add(int gameId, int userId) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql,gameId,new Date(),userId);
    }

    @Override
    public void delete(int gameId, int userId) {
        String sql = "delete from tab_favorite where game_id = ? and user_id = ?";
        template.update(sql,gameId,userId);
    }

    @Override
    public int findTotalCategoryIdCountByUserId(int userId) {
        String sql = "select count(*) from tab_favorite where user_id = ?";
        return template.queryForObject(sql,Integer.class,userId);
    }
}
