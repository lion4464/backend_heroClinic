package com.example.demo.company;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Companymapper {
    CompanyResponse fromEntity(CompanyEntity entity);
    List<CompanyResponse> fromEntityList(List<CompanyEntity> list);
}
