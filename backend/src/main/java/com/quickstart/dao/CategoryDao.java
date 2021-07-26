package com.quickstart.dao;

import com.quickstart.domain.Category;

import java.util.List;

public interface CategoryDao {

    public List<Category> findAll();
}
