package com.quickstart.domain;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private int sex;// 0 is unknown, 1 is man, 2 is woman, 3 is others
    private String telephone;
    private String email;
    private int status;// 0 is notActivated, 1 activated, 2 is baned
    private String activationCode;
}
