package com.quickstart.controller;
import com.quickstart.domain.Category;
import com.quickstart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="",method = RequestMethod.GET)
    public List<Category> getCategoryList(){
        List<Category> categoryList = categoryService.findAll();
        return categoryList;
    }
}
