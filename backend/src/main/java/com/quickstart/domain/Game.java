package com.quickstart.domain;

import lombok.Data;

@Data
public class Game {
    private int gameId;
    private String name;
    private double rate;
    private String introduction;
    private String isShow;
    private String date;
    private int favoriteCount;
    private int clickCount;
    private int categoryId;
    private String image;
    private int developerId;
    private String url;
}
