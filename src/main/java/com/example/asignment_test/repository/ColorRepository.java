package com.example.asignment_test.repository;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findByNameContainingIgnoreCase(String keyword);
}
