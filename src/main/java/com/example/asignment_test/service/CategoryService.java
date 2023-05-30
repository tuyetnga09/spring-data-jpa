package com.example.asignment_test.service;

import com.example.asignment_test.entity.Category;
import com.example.asignment_test.entity.ProductLine;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category insert(Category category);

    Category findById(Integer id);

    boolean delete(Integer id);
}
