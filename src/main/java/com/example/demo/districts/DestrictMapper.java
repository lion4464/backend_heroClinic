package com.example.demo.districts;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DestrictMapper {
    DestrictDTO fromEntity(DestrictEntity obj);
    List<DestrictDTO> fromEntityList(List<DestrictEntity> objlist);
}
