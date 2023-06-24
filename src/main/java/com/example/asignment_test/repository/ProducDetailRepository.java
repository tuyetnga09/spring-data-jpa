package com.example.asignment_test.repository;

import com.example.asignment_test.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducDetailRepository extends JpaRepository<ProductDetail, Integer> {
    List<ProductDetail> findByNameContainingIgnoreCase(String keyword);
    ProductDetail getProductDetailsById(Integer productId);
}
