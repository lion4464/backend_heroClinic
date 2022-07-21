package com.example.demo.analyses;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnalysesMapper {
    AnalysesResponse toDto(AnalysesEntity analyse);
    AnalysesEntity fromDto(AnalysesRequest analyseRequest);
    List<AnalysesResponse> fromPageEntity(List<AnalysesEntity> entityList);
}
