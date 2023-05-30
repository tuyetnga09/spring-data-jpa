package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.Customer;
import com.example.asignment_test.repository.CustomerRepository;
import com.example.asignment_test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
