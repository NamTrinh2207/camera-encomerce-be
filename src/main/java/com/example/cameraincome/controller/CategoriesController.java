package com.example.cameraincome.controller;

import com.example.cameraincome.model.product.Category;
import com.example.cameraincome.service.product.categories.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@CrossOrigin("*")
public class CategoriesController {
    @Autowired
    private ICategoriesService categoriesService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = (List<Category>) categoriesService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
