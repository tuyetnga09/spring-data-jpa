package com.example.asignment_test.repository;

import com.example.asignment_test.entity.Category;
import com.example.asignment_test.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String keyword);
}
