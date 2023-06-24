package com.example.asignment_test.service;

import com.example.asignment_test.entity.Color;
import com.example.asignment_test.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer insert(Customer customer);

    Customer findById(Integer id);

    boolean delete(Integer id);
}
