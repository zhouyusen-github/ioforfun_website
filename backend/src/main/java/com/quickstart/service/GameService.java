package com.quickstart.service;

import com.quickstart.domain.PageBean;
import com.quickstart.domain.Game;

public interface GameService {

    public PageBean<Game> getGameListPage(int categoryId, int currentPage, int pageSize, String name);

    public Game findGameByGameId(int gameId);
}
