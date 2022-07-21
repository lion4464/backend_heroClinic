package com.example.demo.region;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDTO fromEntity(RegionEntity obj);
    List<RegionDTO> fromEntityList(List<RegionEntity> objlist);
}
