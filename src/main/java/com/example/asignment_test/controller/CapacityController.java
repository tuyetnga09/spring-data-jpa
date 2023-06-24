package com.example.asignment_test.controller;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.repository.CapacityRepository;
import com.example.asignment_test.service.CapacityService;
import com.example.asignment_test.service.impl.CapacityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/capacity/")
public class CapacityController {
    @Autowired
    private CapacityService capacityService = new CapacityServiceImpl();
    @Autowired
    private CapacityRepository capacityRepository;

    @GetMapping("display")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listCapcity(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Capacity> capacityList;
        if (keyword != null && !keyword.isEmpty()) {
            capacityList = capacityRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            capacityList = capacityRepository.findAll();
        }
        model.addAttribute("capacityList", capacityList);
        return "capacity";
    }

    @GetMapping("new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String newFormCapcity(Model model) {
        model.addAttribute("capacity", new Capacity());
        return "capacity_form";
    }

    @PostMapping("save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createCapcity(Capacity capacity) {
        capacityService.insert(capacity);
        return "redirect:/admin/capacity/display";
    }

    @GetMapping("edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showEditFormCapacity(@PathVariable("id") Integer id, Model model) {
        Capacity capacity = capacityRepository.findById(id).get();
        model.addAttribute("capacity", capacity);
        return "capacity_form";
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCapacity(@PathVariable("id") Integer id, Model model) {
        Capacity capacity = capacityRepository.findById(id).orElse(null);
        if (capacity != null) {
            capacity.setStatus(1);
            capacityRepository.save(capacity);
        }
        return "redirect:/admin/capacity/display";
    }

}
