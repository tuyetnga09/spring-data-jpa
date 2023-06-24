package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.ProducDetailRepository;
import com.example.asignment_test.repository.ProductLineRepository;
import com.example.asignment_test.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProducDetailRepository producDetailRepository;

    @Override
    public List<ProductDetail> getAll() {
        return producDetailRepository.findAll();
    }


    @Override
    public ProductDetail insert(ProductDetail productDetail) {
        return producDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail findById(Integer id) {
        return producDetailRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<ProductDetail> productLineOptional = producDetailRepository.findById(id);
        if (productLineOptional.isPresent()) {
            producDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDetail getProductDetailsById(Integer productId) {
        // Lấy thông tin sản phẩm từ cơ sở dữ liệu (sử dụng repository)
        Optional<ProductDetail> productDetailsOptional = producDetailRepository.findById(productId);
        return productDetailsOptional.orElse(null);
    }
}
