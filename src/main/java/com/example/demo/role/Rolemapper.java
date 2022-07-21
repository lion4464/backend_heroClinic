package com.example.demo.role;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Rolemapper {
    RoleDto toDto(RoleEntity roleEntity);
    RoleEntity fromDto(RoleRequest roleRequest);
    List<RoleDto> fromPageEntity(List<RoleEntity>  entityList);
}
