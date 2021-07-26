package com.quickstart.dao;

import com.quickstart.domain.Game;

import java.util.List;

public interface GameDao {

    public int findTotalCount(int categoryId,String name);

    public List<Game> findByPage(int categoryId , int start , int pageSize, String name);

    public Game findGameByGameId(int gameId);
}
