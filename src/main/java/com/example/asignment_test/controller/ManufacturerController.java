package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Manufacturer;
import com.example.asignment_test.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manufacturer/")
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("display")
    public String listManufacturer(Model model) {
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        model.addAttribute("manufacturerList", manufacturerList);
        return "manufacturer";
    }

    @GetMapping("new")
    public String newFormManufacturer(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturer_form";
    }

    @PostMapping("save")
    public String createManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturer/display";
    }

    @GetMapping("edit/{id}")
    public String showEditFormManufacturer(@PathVariable("id") Integer id, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).get();
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer_form";
    }
    @GetMapping("delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Integer id, Model model){
        manufacturerRepository.deleteById(id);
        return "redirect:/manufacturer/display";
    }


}
