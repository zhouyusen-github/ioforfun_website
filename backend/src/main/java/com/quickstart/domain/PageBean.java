package com.quickstart.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    private int totalCount; // total number of entity
    private int totalPage; // total pages
    private int currentPage; // Current page number (default is 1)
    private int pageSize; // Number of lines displayed per page
    private List<T> list; // store the entity class data to be displayed
}
