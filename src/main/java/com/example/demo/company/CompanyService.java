package com.example.demo.company;

import com.example.demo.exceptions.DataNotFoundException;
import io.swagger.v3.oas.models.media.UUIDSchema;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    CompanyEntity save(CompanyRequest request);
    CompanyEntity get(UUID id) throws DataNotFoundException;
    CompanyEntity update(CompanyRequest request) throws DataNotFoundException;
    String delete(UUID id);

    List<CompanyEntity> all();
}
