package com.quickstart.service.impl;

import com.quickstart.dao.CategoryDao;
import com.quickstart.domain.Category;
import com.quickstart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Category> findAll() {
        // search redis first
        Set<ZSetOperations.TypedTuple<String>> categoryListRedis = redisTemplate.opsForZSet().rangeByScore("categoryList", 0, -1);
        List<Category> categoryList = null;
        if (categoryListRedis == null || categoryListRedis.size() == 0) {
            // search mysql
            categoryList = categoryDao.findAll();
            // store to redis
            for (int i = 0; i < categoryList.size(); i++) {
                redisTemplate.opsForZSet().add("categoryList", categoryList.get(i).getCategoryName(), categoryList.get(i).getCategoryId());
            }
        } else {
            categoryList = new ArrayList<Category>();
            for (ZSetOperations.TypedTuple<String> tuple : categoryListRedis) {
                Category category = new Category();
                category.setCategoryName(tuple.getValue());
                category.setCategoryId(tuple.getScore().intValue());
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
