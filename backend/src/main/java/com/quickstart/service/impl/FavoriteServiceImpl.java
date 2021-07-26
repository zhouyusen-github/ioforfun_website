package com.quickstart.service.impl;

import com.quickstart.dao.FavoriteDao;
import com.quickstart.dao.GameDao;
import com.quickstart.domain.Favorite;
import com.quickstart.domain.PageBean;
import com.quickstart.domain.Game;
import com.quickstart.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDao favoriteDao;

    @Autowired
    private GameDao gameDao;

    @Override
    public boolean isFavorite(int gameId, int userId) {
        Favorite favorite = favoriteDao.findByGameIdAndUserId(gameId, userId);
        return favorite != null;
    }

    @Override
    public PageBean<Game> getFavoriteList(int currentPage, int pageSize, int userId) {
        PageBean<Game> gamePageBean = new PageBean<Game>();
        gamePageBean.setCurrentPage(currentPage);
        gamePageBean.setPageSize(pageSize);
        int totalCount = favoriteDao.findTotalCategoryIdCountByUserId(userId);
        int start = (currentPage - 1) * pageSize;
        gamePageBean.setTotalCount(totalCount);
        List<Favorite> favoriteList = favoriteDao.findByUserId(start,pageSize,userId);
        List<Game> gameList = new ArrayList<>();
        for (Favorite favorite : favoriteList) {
            Game game = gameDao.findGameByGameId(favorite.getGameId());
            gameList.add(game);
        }
        gamePageBean.setList(gameList);
        return gamePageBean;
    }

    @Override
    public void changeIsFavorite(int gameId, int userId, Boolean isFavoriteBool) {
        Favorite favorite = favoriteDao.findByGameIdAndUserId(gameId, userId);
        if ((favorite==null)&isFavoriteBool) {
            favoriteDao.add(gameId,userId);
        }
        if ((favorite!=null)&(!isFavoriteBool)) {
            favoriteDao.delete(gameId,userId);
        }
    }
}
