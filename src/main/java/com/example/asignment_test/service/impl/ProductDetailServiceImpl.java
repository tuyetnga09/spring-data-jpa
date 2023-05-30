package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.ProducDetailRepository;
import com.example.asignment_test.repository.ProductLineRepository;
import com.example.asignment_test.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
