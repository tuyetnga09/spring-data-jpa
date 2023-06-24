package com.example.asignment_test.controller;

import com.example.asignment_test.entity.bill.BillProduct;
import com.example.asignment_test.repository.BillProductRepository;
import com.example.asignment_test.service.BillProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill-product")
public class BillProductController {
    @Autowired
    private BillProductService billProductService;

    private List<BillProduct> billProducts = new ArrayList<BillProduct>();

    @GetMapping("/display")
    public String getDisplay(Model model){
        billProducts = billProductService.getAllBillProduct();
        model.addAttribute("bill", billProducts);
        return "billProduct";
    }
}
