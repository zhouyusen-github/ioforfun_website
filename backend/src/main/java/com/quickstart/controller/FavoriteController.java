package com.quickstart.controller;

import com.quickstart.domain.PageBean;
import com.quickstart.domain.ResultInfo;
import com.quickstart.domain.Game;
import com.quickstart.domain.User;
import com.quickstart.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/gameId/{gameId}", method = RequestMethod.GET)
    public boolean isFavorite(@PathVariable int gameId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId;
        if(user == null){
            return false;
        }else{
            userId = user.getUserId();
            boolean flag = favoriteService.isFavorite(gameId, userId);
            return flag;
        }
    }

    @RequestMapping(value = "/gameId/{gameId}", method = RequestMethod.PUT)
    public ResultInfo changeIsFavorite(@PathVariable int gameId, @RequestParam("isFavoriteBool") boolean isFavoriteBool, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResultInfo info = new ResultInfo();
        int userId;
        if(user == null){
            info.setFlag(false);
        }else{
            userId = user.getUserId();
            try{
                favoriteService.changeIsFavorite(gameId, userId,isFavoriteBool);
                info.setFlag(true);
                info.setData(isFavoriteBool);
            } catch(Exception e) {
                info.setFlag(false);
                e.printStackTrace();
            }
        }
        return info;
    }

    /**
     * @methodsName: Favorite
     * @description: return all favorite game of this user
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageBean<Game> Favorite(@RequestParam(value="currentPage",required = false,defaultValue = "0") int currentPage,
                                   @RequestParam(value="pageSize",required = false,defaultValue = "5") int pageSize,
                                   HttpSession session) {
        User user = (User) session.getAttribute("user");
        PageBean<Game> pageBean = null;
        if(user == null){
            pageBean = new PageBean<Game>();
        }else{
            int userId = user.getUserId();
            pageBean = favoriteService.getFavoriteList(currentPage, pageSize,userId);
        }
        return pageBean;
    }
}
