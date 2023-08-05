package com.example.demo.region;

import com.example.demo.exceptions.DataNotFoundException;

import java.util.List;

public interface RegionService {
    RegionEntity get(Long id) throws DataNotFoundException;
    RegionEntity update(RegionDTO request) throws DataNotFoundException;
    List<RegionEntity> all();
}
