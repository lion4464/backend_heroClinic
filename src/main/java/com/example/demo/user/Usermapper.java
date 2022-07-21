package com.example.demo.user;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Usermapper {
    UserDto toDto(UserEntity userEntity);
    List<UserDto> fromPageEntity(List<UserEntity> userEntityList);
}
