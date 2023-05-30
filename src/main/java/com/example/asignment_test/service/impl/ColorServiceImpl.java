package com.example.asignment_test.service.impl;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.Color;
import com.example.asignment_test.repository.CapacityRepository;
import com.example.asignment_test.repository.ColorRepository;
import com.example.asignment_test.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color insert(Color color) {
        if (color.getId() == null) {
            color.setDateCreate(new Date());
            color.setDateCorrect(new Date());
        } else {
            Color existingColor = findById(color.getId());
            if (existingColor != null) {
                color.setDateCreate(existingColor.getDateCreate());
                color.setDateCorrect(new Date());
            }
        }
        return colorRepository.save(color);
    }

    @Override
    public Color findById(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Color> colorOptional = colorRepository.findById(id);
        if (colorOptional.isPresent()) {
            colorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
