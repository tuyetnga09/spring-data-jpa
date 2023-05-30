package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Color;
import com.example.asignment_test.entity.Manufacturer;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.ManufacturerRepository;
import com.example.asignment_test.repository.ProductLineRepository;
import com.example.asignment_test.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product-line/")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;
    @Autowired
    private ProductLineRepository productLineRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("display")
    public String listProductLine(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<ProductLine> productLine;
        if (keyword != null && !keyword.isEmpty()) {
            productLine = productLineRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            productLine = productLineRepository.findAll();
        }
        model.addAttribute("productLine", productLine);
        return "product-line";
    }


    @GetMapping("new")
    public String newFormProductLine(Model model) {
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        model.addAttribute("productLine", new ProductLine());
        model.addAttribute("manufacturerList", manufacturerList);
        return "product-line-form";
    }

    @PostMapping("save")
    public String createProductLine(@ModelAttribute("productLine") ProductLine productLine, BindingResult bindingResult) {
        productLineService.insert(productLine);
        return "redirect:/product-line/display";
    }

    @GetMapping("edit/{id}")
    public String showEditFormProduct(@PathVariable("id") Integer id, Model model) {
        ProductLine productLine = productLineRepository.findById(id).get();
        model.addAttribute("productLine", productLine);
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        model.addAttribute("manufacturerList", manufacturerList);
        return "product-line-form";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        ProductLine productLine = productLineRepository.findById(id).orElse(null);
        if (productLine != null) {
            productLine.setStatus(1);
            productLineRepository.save(productLine);
        }
        return "redirect:/product-line/display";
    }

}
