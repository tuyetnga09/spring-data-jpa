package com.example.asignment_test.service;


import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.repository.CapacityRepository;

import java.util.List;

public interface CapacityService {
    List<Capacity> getAll();
    Capacity insert(Capacity capacity);
    Capacity findById(Integer id);
    boolean delete(Integer id);
}
