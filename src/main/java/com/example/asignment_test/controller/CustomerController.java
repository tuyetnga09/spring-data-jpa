package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Customer;
import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.entity.bill.BillProduct;
import com.example.asignment_test.service.BillProductService;
import com.example.asignment_test.service.CustomerService;
import com.example.asignment_test.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private BillProductService billProductService;

    private List<ProductDetail> productDetailList = new ArrayList<>();

    @GetMapping("/display")
    public String listColor(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Customer> customerList;
        customerList = customerService.getAll();
        model.addAttribute("customer", customerList);
        return "customer";
    }

    @GetMapping("new")
    public String newCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("save")
    public String createCustomer(@ModelAttribute("customer") Customer customer,  @RequestParam("productId") Integer productId) {
        customerService.insert(customer);
        ProductDetail productDetails = productDetailService.getProductDetailsById(productId);
        BillProduct billProduct = new BillProduct();
        billProduct.setNumber(1);  // Số lượng sản phẩm
        billProduct.setUnitPrice(productDetails.getCategory().getProductLine().getPrice());  // Giá bán sản phẩm
        billProduct.setAmount(productDetails.getCategory().getProductLine().getPrice());  // Tổng tiền
        // Lưu đối tượng Bill_Product vào cơ sở dữ liệu
        billProductService.saveBillProduct(billProduct);
        return "redirect:/customer/display";
    }


    @GetMapping("edit/{id}")
    public String showEditFormCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }


}
