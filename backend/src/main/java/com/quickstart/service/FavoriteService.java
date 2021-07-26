package com.quickstart.service;

import com.quickstart.domain.PageBean;
import com.quickstart.domain.Game;

public interface FavoriteService {

    public boolean isFavorite(int gameId, int userId);

    public PageBean<Game> getFavoriteList(int currentPage, int pageSize, int userId);

    void changeIsFavorite(int gameId, int userId, Boolean isFavoriteBool);
}
