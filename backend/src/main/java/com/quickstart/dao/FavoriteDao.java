package com.quickstart.dao;

import com.quickstart.domain.Favorite;

import java.util.List;

public interface FavoriteDao {

    public Favorite findByGameIdAndUserId(int gameId, int userId);

    public List<Favorite> findByUserId(int start,int pageSize, int userId);

    public int findCountByGameId(int gameId);

    void add(int i, int userId);

    public void delete(int gameId, int userId);

    int findTotalCategoryIdCountByUserId(int userId);
}
