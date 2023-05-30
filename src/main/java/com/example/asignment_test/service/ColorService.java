package com.example.asignment_test.service;

import com.example.asignment_test.entity.Capacity;
import com.example.asignment_test.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> getAll();

    Color insert(Color color);

    Color findById(Integer id);

    boolean delete(Integer id);

}
