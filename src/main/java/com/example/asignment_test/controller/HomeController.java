package com.example.asignment_test.controller;

import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.repository.CategoryRepository;
import com.example.asignment_test.repository.ProducDetailRepository;
import com.example.asignment_test.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class HomeController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProducDetailRepository producDetailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String listProductDetail( Model model) {
        List<ProductDetail> productDetails;
        productDetails = producDetailRepository.findAll();
        model.addAttribute("productDetail", productDetails);
        return "trang-chu";
    }


}
