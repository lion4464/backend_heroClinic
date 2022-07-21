package com.example.demo.salary_type;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryTypemapper {
    SalaryTypeDto toDto(SalaryTypeEntity entity);
    SalaryTypeEntity fromDto(SalaryTypeRequest request);
    List<SalaryTypeDto> fromPageEntity(List<SalaryTypeEntity> entityList);
}

