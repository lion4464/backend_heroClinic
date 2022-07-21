package com.example.demo.districts;

import java.util.List;

public interface DestrictService {
    DestrictEntity get(Long id);
    DestrictEntity update(DestrictDTO request);
    List<DestrictEntity> all();
}
