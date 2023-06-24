package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.bill.Bill;
import com.example.asignment_test.repository.BillRepository;
import com.example.asignment_test.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getBillList() {
        return billRepository.findAll();
    }

    @Override
    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }
}
