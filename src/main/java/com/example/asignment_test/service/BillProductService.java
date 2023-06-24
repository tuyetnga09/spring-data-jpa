package com.example.asignment_test.service;

import com.example.asignment_test.entity.bill.Bill;
import com.example.asignment_test.entity.bill.BillProduct;

import java.util.List;

public interface BillProductService {
    List<BillProduct> getAllBillProduct();
   void saveBillProduct(BillProduct billProduct);
}
