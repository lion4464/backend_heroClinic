package com.example.demo.company;

import io.swagger.v3.oas.models.media.UUIDSchema;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    CompanyEntity save(CompanyRequest request);
    CompanyEntity get(UUID id);
    CompanyEntity update(CompanyRequest request);
    String delete(UUID id);

    List<CompanyEntity> all();
}
