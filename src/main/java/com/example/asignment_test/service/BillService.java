package com.example.asignment_test.service;


import com.example.asignment_test.entity.bill.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getBillList();
    void saveBill(Bill bill);
}
