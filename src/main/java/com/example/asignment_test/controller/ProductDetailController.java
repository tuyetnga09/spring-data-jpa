package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Category;
import com.example.asignment_test.entity.ProductDetail;
import com.example.asignment_test.repository.CategoryRepository;
import com.example.asignment_test.repository.ProducDetailRepository;
import com.example.asignment_test.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/productDetail/")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProducDetailRepository producDetailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/display")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String listProductDetail(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<ProductDetail> productDetails;
        if (keyword != null && !keyword.isEmpty()) {
            productDetails = producDetailRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            productDetails = producDetailRepository.findAll();
        }
        model.addAttribute("productDetail", productDetails);
        return "product-detail";
    }


    @GetMapping("new")
    public String newFormProductDetail(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("productDetail", new ProductDetail());
        model.addAttribute("category", categoryList);
        return "product-detail-form";
    }

    @PostMapping("save")
    public String createProductDetail(@ModelAttribute ProductDetail productDetail, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Path fileUpload = Paths.get("src/main/resources/static/images");
            if (!Files.exists(fileUpload)) {
                Files.createDirectories(fileUpload);
            }

            try {
                Path pathFile = fileUpload.resolve(fileName);
                Files.copy(file.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
                productDetail.setImages(fileName);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
        }

        producDetailRepository.save(productDetail);
        return "redirect:/productDetail/display";
    }


    @GetMapping("edit/{id}")
    public String showEditFormProduct(@PathVariable("id") Integer id, Model model) {
        ProductDetail productDetail = producDetailRepository.findById(id).get();
        model.addAttribute("productDetail", productDetail);
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("category", categoryList);
        return "product-detail-form";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        ProductDetail productDetail = producDetailRepository.findById(id).orElse(null);
        if (productDetail != null) {
            productDetail.setStatus(1);
            producDetailRepository.save(productDetail);
        }
        return "redirect:/productDetail/display";
    }


}
