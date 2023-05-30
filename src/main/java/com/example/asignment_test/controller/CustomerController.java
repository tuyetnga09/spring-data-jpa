package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Color;
import com.example.asignment_test.entity.Customer;
import com.example.asignment_test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/khach-hang")
    public String listColor(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Customer> customerList;
        customerList = customerService.getAll();
        model.addAttribute("customer", customerList);
        return "customer";
    }


}
