package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.Category;
import com.example.asignment_test.entity.Color;
import com.example.asignment_test.entity.Manufacturer;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.CapacityRepository;
import com.example.asignment_test.repository.CategoryRepository;
import com.example.asignment_test.repository.ColorRepository;
import com.example.asignment_test.repository.ManufacturerRepository;
import com.example.asignment_test.repository.ProductLineRepository;
import com.example.asignment_test.service.CapacityService;
import com.example.asignment_test.service.CategoryService;
import com.example.asignment_test.service.ColorService;
import com.example.asignment_test.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductLineRepository productLineRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private CapacityRepository capacityRepository;

    @GetMapping("display")
    public String listCategory(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Category> categoryList;
        if (keyword != null && !keyword.isEmpty()) {
            categoryList = categoryRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            categoryList = categoryRepository.findAll();
        }
        model.addAttribute("category", categoryList);
        return "category";
    }


    @GetMapping("new")
    public String newFormCategory(Model model) {

        List<Color> colorList = colorRepository.findAll();
        model.addAttribute("color", colorList);

        List<ProductLine> productLineList = productLineRepository.findAll();
        model.addAttribute("product", productLineList);

        List<Capacity> capacityList = capacityRepository.findAll();
        model.addAttribute("capacity", capacityList);

        model.addAttribute("category", new Category());
        return "category_form";
    }

    @PostMapping("save")
    public String createCategory(@ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println("Lỗi trường " + error.getField() + ": " + error.getDefaultMessage());
            }
            return "category_form";
        }

        categoryService.insert(category);
        return "redirect:/category/display";
    }

    @GetMapping("edit/{id}")
    public String showEditFormCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryRepository.findById(id).get();
        model.addAttribute("category", category);
        List<Color> colorList = colorRepository.findAll();
        model.addAttribute("color", colorList);
        List<ProductLine> productLineList = productLineRepository.findAll();
        model.addAttribute("product", productLineList);
        List<Capacity> capacityList = capacityRepository.findAll();
        model.addAttribute("capacity", capacityList);
        return "category_form";
    }

    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setStatus(1);
            categoryRepository.save(category);
        }
        return "redirect:/category/display";
    }

}
