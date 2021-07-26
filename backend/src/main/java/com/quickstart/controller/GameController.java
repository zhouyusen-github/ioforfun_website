package com.quickstart.controller;

import com.quickstart.domain.*;
import com.quickstart.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * @methodsName: getGameList
     * @description: return list of game of this category
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public PageBean<Game> getGameListPage(@RequestParam(value="categoryId",required = false,defaultValue = "0") int categoryId,
                                       @RequestParam(value="currentPage", required = false, defaultValue = "0") int currentPage,
                                       @RequestParam(value="pageSize",required = false,defaultValue = "5") int pageSize,
                                       @RequestParam(value="name",required = false,defaultValue = "") String name) {
        PageBean<Game> gamePageBean = gameService.getGameListPage(categoryId, currentPage, pageSize,name);
        return gamePageBean;
    }

    @RequestMapping(value = "/gameId/{gameId}",method = RequestMethod.GET)
    public Game getOneGame(@PathVariable int gameId) {
        Game game = gameService.findGameByGameId(gameId);
        return game;
    }
}
