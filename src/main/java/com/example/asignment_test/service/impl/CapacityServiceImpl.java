package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.ProductLine;
import com.example.asignment_test.repository.CapacityRepository;
import com.example.asignment_test.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapacityServiceImpl implements CapacityService {
    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public List<Capacity> getAll() {
        return capacityRepository.findAll();
    }

    @Override
    public Capacity insert(Capacity capacity) {
        return capacityRepository.save(capacity);
    }

    @Override
    public Capacity findById(Integer id) {
        return capacityRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Capacity> capacityOptional = capacityRepository.findById(id);
        if (capacityOptional.isPresent()) {
           capacityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
