package com.example.demo.room_place;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomPlaceMapper {
    RoomPlaceResponce toDto(RoomPlaceEntity roomPlace);

    List<RoomPlaceResponce> fromEntityList(List<RoomPlaceEntity> roomPlaceEntityList);
}
