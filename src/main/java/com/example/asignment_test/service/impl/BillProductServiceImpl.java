package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.bill.BillProduct;
import com.example.asignment_test.repository.BillProductRepository;
import com.example.asignment_test.service.BillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillProductServiceImpl implements BillProductService {
    @Autowired
    private BillProductRepository billProductRepository;

    @Override
    public List<BillProduct> getAllBillProduct() {
        return billProductRepository.findAll();
    }

    @Override
    public void saveBillProduct(BillProduct billProduct) {
        billProductRepository.save(billProduct);
    }
}
