package com.quickstart.service.impl;

import com.quickstart.dao.FavoriteDao;
import com.quickstart.dao.GameDao;
import com.quickstart.domain.PageBean;
import com.quickstart.domain.Game;
import com.quickstart.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public PageBean<Game> getGameListPage(int categoryId, int currentPage, int pageSize, String name ) {
        PageBean<Game> gamePageBean = new PageBean<Game>();
        gamePageBean.setCurrentPage(currentPage);
        gamePageBean.setPageSize(pageSize);
        int totalCount = gameDao.findTotalCount(categoryId,name);
        gamePageBean.setTotalCount(totalCount);
        int start = (currentPage - 1) * pageSize;
        List<Game> list = gameDao.findByPage(categoryId,start,pageSize,name);
        gamePageBean.setList(list);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        gamePageBean.setTotalPage(totalPage);
        return gamePageBean;
    }

    @Override
    public Game findGameByGameId(int gameId) {
        Game game = gameDao.findGameByGameId(gameId);
        int count = favoriteDao.findCountByGameId(game.getGameId());
        game.setFavoriteCount(count);
        return game;
    }
}
