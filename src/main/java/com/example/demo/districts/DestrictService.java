package com.example.demo.districts;

import com.example.demo.exceptions.DataNotFoundException;

import java.util.List;

public interface DestrictService {
    DestrictEntity get(Long id) throws DataNotFoundException;
    DestrictEntity update(DestrictDTO request) throws DataNotFoundException;
    List<DestrictEntity> all();
}
