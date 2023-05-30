package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.ProductLineRepository;
import com.example.asignment_test.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductLineServiceImpl implements ProductLineService {
    @Autowired
    private ProductLineRepository productLineRepository;

    @Override
    public List<ProductLine> getAll() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine insert(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public ProductLine findById(Integer id) {
        return productLineRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<ProductLine> productLineOptional = productLineRepository.findById(id);
        if (productLineOptional.isPresent()) {
            productLineRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
