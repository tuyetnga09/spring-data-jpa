package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.Color;
import com.example.asignment_test.repository.CapacityRepository;
import com.example.asignment_test.repository.ColorRepository;
import com.example.asignment_test.service.CapacityService;
import com.example.asignment_test.service.ColorService;
import com.example.asignment_test.service.impl.CapacityServiceImpl;
import com.example.asignment_test.service.impl.ColorServiceImpl;
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
import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Controller
@RequestMapping("/color/")
public class ColorController {
    @Autowired
    private ColorService colorService;
    @Autowired
    private ColorRepository colorRepository;

    @GetMapping("display")
    public String listColor(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Color> colorList;
        if (keyword != null && !keyword.isEmpty()) {
            colorList = colorRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            colorList = colorRepository.findAll();
        }
        model.addAttribute("colorList", colorList);
        return "color";
    }

    @GetMapping("new")
    public String newFormColor(Model model) {
        model.addAttribute("color", new Color());
        return "color_form";
    }


    @PostMapping("/save")
    public String createCategory(@ModelAttribute Color categoryForm, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            Path fileUpload = Paths.get("src/main/resources/static/images");
            if (!Files.exists(fileUpload)) {
                Files.createDirectories(fileUpload);
            }

            try {
                Path pathFile = fileUpload.resolve(fileName);
                Files.copy(file.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
                categoryForm.setImages(fileName);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
        }

            colorService.insert(categoryForm);
        return "redirect:/color/display";
    }


    @GetMapping("edit/{id}")
    public String showEditFormColor(@PathVariable("id") Integer id, Model model) {
        Color color = colorRepository.findById(id).get();
        model.addAttribute("color", color);
        return "color_form";
    }

    @GetMapping("delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Color color = colorRepository.findById(id).orElse(null);
        if (color != null) {
            color.setStatus(1);
            colorRepository.save(color);
        }
        return "redirect:/color/display";
    }

}
