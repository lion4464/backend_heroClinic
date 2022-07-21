package com.example.demo.department;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Departmentmapper {
    DepartmentDto toDto(DepartmentEntity entity);
    DepartmentEntity fromDto(DepartmentRequest request);
    List<DepartmentDto> fromPageEntity(List<DepartmentEntity> entityList);
}

