package com.quickstart.domain;

import lombok.Data;

@Data
public class Favorite {
    private int gameId;
    private String date;
    private int userId;
}
