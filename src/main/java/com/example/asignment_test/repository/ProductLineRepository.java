package com.example.asignment_test.repository;

import com.example.asignment_test.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Integer> {
    List<ProductLine> findByNameContainingIgnoreCase(String keyword);
}
