package com.example.asignment_test.service;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.ProductLine;

import java.util.List;

public interface ProductLineService {
    List<ProductLine> getAll();
    ProductLine insert(ProductLine productLine);
    ProductLine findById(Integer id);
    boolean delete(Integer id);
}
