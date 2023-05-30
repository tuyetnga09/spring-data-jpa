package com.example.asignment_test.service;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.ProductLine;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAll();

    ProductDetail insert(ProductDetail productDetail);

    ProductDetail findById(Integer id);

    boolean delete(Integer id);
}
