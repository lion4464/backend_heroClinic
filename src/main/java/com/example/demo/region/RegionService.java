package com.example.demo.region;

import java.util.List;

public interface RegionService {
    RegionEntity get(Long id);
    RegionEntity update(RegionDTO request);
    List<RegionEntity> all();
}
